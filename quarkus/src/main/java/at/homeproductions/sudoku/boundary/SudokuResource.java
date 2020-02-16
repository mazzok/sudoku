package at.homeproductions.sudoku.boundary;


import at.homeproductions.sudoku.converter.SudokuConverter;
import at.homeproductions.sudoku.entity.FieldVincinityCalculator;
import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.FieldValueChangedModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sudoku")
public class SudokuResource {

    @Path("solve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response solve() {
        Sudoku s = new Sudoku();
        s.addInitialField(1,0,3);
        s.addInitialField(3,1,1);
        s.addInitialField(4,1,9);
        s.addInitialField(5,1,5);
        s.addInitialField(2,2,8);
        s.addInitialField(7,2,6);
        s.addInitialField(0,3,8);
        s.addInitialField(4,3,6);
        s.addInitialField(0,4,4);
        s.addInitialField(3,4,8);
        s.addInitialField(8,4,1);
        s.addInitialField(4,5,2);
        s.addInitialField(1,6,6);
        s.addInitialField(6,6,2);
        s.addInitialField(7,6,8);
        s.addInitialField(3,7,4);
        s.addInitialField(4,7,1);
        s.addInitialField(5,7,9);
        s.addInitialField(8,7,5);
        s.addInitialField(7,8,7);

        s.solve();
        return Response.ok(new SudokuConverter().toModel(s)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response unsolved() {
        Sudoku s = new Sudoku();
        s.addInitialField(1,0,3);
        s.addInitialField(3,1,1);
        s.addInitialField(4,1,9);
        s.addInitialField(5,1,5);
        s.addInitialField(2,2,8);
        s.addInitialField(7,2,6);
        s.addInitialField(0,3,8);
        s.addInitialField(4,3,6);
        s.addInitialField(0,4,4);
        s.addInitialField(3,4,8);
        s.addInitialField(8,4,1);
        s.addInitialField(4,5,2);
        s.addInitialField(1,6,6);
        s.addInitialField(6,6,2);
        s.addInitialField(7,6,8);
        s.addInitialField(3,7,4);
        s.addInitialField(4,7,1);
        s.addInitialField(5,7,9);
        s.addInitialField(8,7,5);
        s.addInitialField(7,8,7);

        return Response.ok(new SudokuConverter().toModel(s)).build();
    }

    @POST
    @Path("setfieldvalue")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setFieldValue(FieldValueChangedModel fieldValueChangedModel){
        Sudoku sudoku = new SudokuConverter().toEntity(fieldValueChangedModel.getSudokuModel());
        SudokuField f = sudoku.getField(fieldValueChangedModel.getBlockX(), fieldValueChangedModel.getBlockY(), fieldValueChangedModel.getFieldX(), fieldValueChangedModel.getFieldY());

        //set field value
        f.setValue(fieldValueChangedModel.getValue(), false);
        //set all other field values which are set currently to the picked value to null
        List<SudokuField> sameValueFields = FieldVincinityCalculator.getFieldsWithSameValue(f);
        sameValueFields.stream().forEach(s -> s.setValue(null));
        sameValueFields.stream().forEach(s -> FieldVincinityCalculator.hideOrUnhidePossibleValues(sudoku, s));

        FieldVincinityCalculator.hideOrUnhidePossibleValues(sudoku, f);
        return Response.ok(new SudokuConverter().toModel(sudoku)).build();
    }

}
