package at.homeproductions.sudoku.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuSnapshot extends Sudoku {
    private String message;

//    private SudokuSnapshot (Sudoku at.homeproductions.sudoku) {
//
//    }

//    public SudokuSnapshot(String message, Sudoku at.homeproductions.sudoku) {
//        this(at.homeproductions.sudoku);
//        this.message = message;
//    }

    public SudokuSnapshot(String message, List<SudokuSnapshotField> actors, List<SudokuSnapshotField> reactors, Sudoku sudoku) {
        for(int y = 0; y < this.blocks.length;y++ ) {
            for(int x = 0; x < this.blocks[0].length;x++) {
                this.blocks[y][x] = new SudokuSnapshotBlock(sudoku.blocks[y][x]);
            }
        }

        matchFieldsByCoords(actors).stream().map(s -> (SudokuSnapshotField)s).forEach(s -> s.setActor(true));
        matchFieldsByCoords(reactors).stream().map(s -> (SudokuSnapshotField)s).forEach(s -> s.setReactor(true));
    }

    private List<SudokuField> matchFieldsByCoords(List<SudokuSnapshotField> reference) {
        return Arrays.stream(this.blocks)
                .flatMap(Arrays::stream)
                .map(SudokuBlock::getFields)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .filter(s -> reference.stream().anyMatch(ss -> ss.equalsCoordinates(s.getBlock().getY(), s.getBlock().getX(), s.getY(), s.getX())))
                .collect(Collectors.toList());
    }

    public String getMessage() {
        return this.message;
    }
}
