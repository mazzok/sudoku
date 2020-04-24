package at.homeproductions.generatedsudoku.boundary;


import at.homeproductions.sudoku.converter.generated.GeneratedSudokuConverter;
import at.homeproductions.sudoku.entity.generator.GeneratedSudoku;
import at.homeproductions.sudoku.generator.SudokuGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("generatedsudoku")
public class GeneratedSudokuResource {

    @Path("generate")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generate() {
        GeneratedSudoku s = SudokuGenerator.generate();
        return Response.ok(new GeneratedSudokuConverter().toModel(s)).build();
    }

}
