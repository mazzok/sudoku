package at.homeproductions.sudoku.boundary;


import at.homeproductions.sudoku.converter.SudokuConverter;
import at.homeproductions.sudoku.converter.snapshot.SudokuSnapshotConverter;
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

        s = _debugStep(s, 0,0,0,1,6);
        s = _debugStep(s, 2,2,1,1,3);
        s = _debugStep(s, 2,2,0,1,6);
        s = _debugStep(s, 2,0,1,0,1);
        s = _debugStep(s, 0,1,2,1,6);
        s = _debugStep(s, 0,2,1,1,8);
        return Response.ok(new SudokuConverter().toModel(s)).build();
    }

    private Sudoku _debugStep(Sudoku s, int blockX, int blockY, int fieldX, int fielY, int value) {
        SudokuModel model = new SudokuConverter().toModel(s);
        FieldValueChangedModel m = new FieldValueChangedModel();
        m.setBlockX(blockX);
        m.setBlockY(blockY);
        m.setFieldX(fieldX);
        m.setFieldY(fielY);
        m.setValue(value);
        m.setSudokuModel(model);
        return internSetFieldValue(m);
    }

    @POST
    @Path("setfieldvalue")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setFieldValue(FieldValueChangedModel fieldValueChangedModel){
        Sudoku sudoku = internSetFieldValue(fieldValueChangedModel);
        return Response.ok(new SudokuConverter().toModel(sudoku)).build();
    }

    private Sudoku internSetFieldValue(FieldValueChangedModel fieldValueChangedModel) {
        Sudoku sudoku = new SudokuConverter().toEntity(fieldValueChangedModel.getSudokuModel());

        SudokuField f = sudoku.getField(fieldValueChangedModel.getBlockX(), fieldValueChangedModel.getBlockY(), fieldValueChangedModel.getFieldX(), fieldValueChangedModel.getFieldY());

        //set field value
        f.setValue(fieldValueChangedModel.getValue(), true);
        //set all other field values which are set currently to the picked value to null
        List<SudokuField> sameValueFields = FieldVincinityCalculator.getFieldsWithSameValue(f);
        sameValueFields.stream().forEach(s -> s.setValue(null));
        sameValueFields.stream().forEach(s -> FieldVincinityCalculator.hideOrUnhidePossibleValues(sudoku, s));

        FieldVincinityCalculator.hideOrUnhidePossibleValues(sudoku, f);
        return sudoku;
    }

}
