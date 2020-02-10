package at.homeproductions.sudoku.boundary;


import at.homeproductions.sudoku.converter.SudokuConverter;
import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.FieldValueChangedModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sudoku")
public class SudokuResource {

    @Path("solve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response solve() {
        Sudoku s = new Sudoku();
        s.addStandardConfigField(1,0,3);
        s.addStandardConfigField(3,1,1);
        s.addStandardConfigField(4,1,9);
        s.addStandardConfigField(5,1,5);
        s.addStandardConfigField(2,2,8);
        s.addStandardConfigField(7,2,6);
        s.addStandardConfigField(0,3,8);
        s.addStandardConfigField(4,3,6);
        s.addStandardConfigField(0,4,4);
        s.addStandardConfigField(3,4,8);
        s.addStandardConfigField(8,4,1);
        s.addStandardConfigField(4,5,2);
        s.addStandardConfigField(1,6,6);
        s.addStandardConfigField(6,6,2);
        s.addStandardConfigField(7,6,8);
        s.addStandardConfigField(3,7,4);
        s.addStandardConfigField(4,7,1);
        s.addStandardConfigField(5,7,9);
        s.addStandardConfigField(8,7,5);
        s.addStandardConfigField(7,8,7);

        s.solve();
        return Response.ok(new SudokuConverter().toModel(s)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response unsolved() {
        Sudoku s = new Sudoku();
        s.addStandardConfigField(1,0,3);
        s.addStandardConfigField(3,1,1);
        s.addStandardConfigField(4,1,9);
        s.addStandardConfigField(5,1,5);
        s.addStandardConfigField(2,2,8);
        s.addStandardConfigField(7,2,6);
        s.addStandardConfigField(0,3,8);
        s.addStandardConfigField(4,3,6);
        s.addStandardConfigField(0,4,4);
        s.addStandardConfigField(3,4,8);
        s.addStandardConfigField(8,4,1);
        s.addStandardConfigField(4,5,2);
        s.addStandardConfigField(1,6,6);
        s.addStandardConfigField(6,6,2);
        s.addStandardConfigField(7,6,8);
        s.addStandardConfigField(3,7,4);
        s.addStandardConfigField(4,7,1);
        s.addStandardConfigField(5,7,9);
        s.addStandardConfigField(8,7,5);
        s.addStandardConfigField(7,8,7);

        return Response.ok(new SudokuConverter().toModel(s)).build();
    }

    @POST
    @Path("setfieldvalue")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculateCandidates(FieldValueChangedModel fieldValueChangedModel){
        Sudoku sudoku = new SudokuConverter().toEntity(fieldValueChangedModel.getSudokuModel());
        SudokuField f = sudoku.getField(fieldValueChangedModel.getBlockX(), fieldValueChangedModel.getBlockY(), fieldValueChangedModel.getFieldX(), fieldValueChangedModel.getFieldY());
        SudokuField[] row = sudoku.getRow(sudoku.getRowIndex(f));
        SudokuField[] column = sudoku.getColumn(sudoku.getColIndex(f));
        f.setValue(fieldValueChangedModel.getValue(), row, column);
//        sudoku.calculateCandidates();
        return Response.ok(new SudokuConverter().toModel(sudoku)).build();
    }

}
