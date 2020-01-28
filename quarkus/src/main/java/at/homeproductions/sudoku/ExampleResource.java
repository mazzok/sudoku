package at.homeproductions.sudoku;

import at.homeproductions.sudoku.boundary.SudokuResource;
import at.homeproductions.sudoku.converter.SudokuConverter;
import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.model.SudokuModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sudoku")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response solve() {
       return new SudokuResource().unsolved();
    }

    @POST
    @Path("calculatecandidates")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculateCandidates(SudokuModel sudokuModel){
        return new SudokuResource().calculateCandidates(sudokuModel);
    }
}