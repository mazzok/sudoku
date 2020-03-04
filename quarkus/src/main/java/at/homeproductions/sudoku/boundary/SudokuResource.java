package at.homeproductions.sudoku.boundary;


import at.homeproductions.sudoku.converter.SudokuConverter;
import at.homeproductions.sudoku.converter.SudokuSnapshotConverter;
import at.homeproductions.sudoku.entity.FieldVincinityCalculator;
import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.FieldValueChangedModel;
import at.homeproductions.sudoku.model.SudokuModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sudoku")
public class SudokuResource {

    @Path("solve")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response solve() {
        Sudoku s = Sudoku.getDefaulSudoku();
        s.solve();
        return Response.ok(new SudokuConverter().toModel(s)).build();
    }

    @Path("solve/step")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response solveStep(SudokuModel model) {
        Sudoku s = new SudokuConverter().toEntity(model);
        s.getSnapshots().clear();
        s.solveSteps(1l);
        if (s.getSnapshots().isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(new SudokuSnapshotConverter().toModelList(s.getSnapshots())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response unsolved() {
        Sudoku s = Sudoku.getDefaulSudoku();
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
        f.setValue(fieldValueChangedModel.getValue(), true);
        //set all other field values which are set currently to the picked value to null
        List<SudokuField> sameValueFields = FieldVincinityCalculator.getFieldsWithSameValue(f);
        sameValueFields.stream().forEach(s -> s.setValue(null));
        sameValueFields.stream().forEach(s -> FieldVincinityCalculator.hideOrUnhidePossibleValues(sudoku, s));

        FieldVincinityCalculator.hideOrUnhidePossibleValues(sudoku, f);
        return Response.ok(new SudokuConverter().toModel(sudoku)).build();
    }

}
