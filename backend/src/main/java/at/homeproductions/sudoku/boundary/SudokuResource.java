package at.homeproductions.sudoku.boundary;


import at.homeproductions.sudoku.converter.SudokuConverter;
import at.homeproductions.sudoku.entity.Sudoku;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("sudoku")
public class SudokuResource {

    @Path("solve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response solve() {
        Sudoku s = new Sudoku();
        s.addField(1,0,3);
        s.addField(3,1,1);
        s.addField(4,1,9);
        s.addField(5,1,5);
        s.addField(2,2,8);
        s.addField(7,2,6);
        s.addField(0,3,8);
        s.addField(4,3,6);
        s.addField(0,4,4);
        s.addField(3,4,8);
        s.addField(8,4,1);
        s.addField(4,5,2);
        s.addField(1,6,6);
        s.addField(6,6,2);
        s.addField(7,6,8);
        s.addField(3,7,4);
        s.addField(4,7,1);
        s.addField(5,7,9);
        s.addField(8,7,5);
        s.addField(7,8,7);

        s.solve();
        return Response.ok(SudokuConverter.fromEntity(s)).build();
    }
}
