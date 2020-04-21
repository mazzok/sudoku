package at.homeproductions.sudoku.entity.snapshot;

import at.homeproductions.sudoku.entity.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuSnapshot extends AbstractSudoku<SudokuSnapshotField, SudokuSnapshotBlock, SudokuSnapshot> {
    private String message;

    public SudokuSnapshot(){
        super();
    }
    public SudokuSnapshot(String message, List<? extends AbstractSudokuField> actors, List<? extends AbstractSudokuField> reactors, AbstractSudoku sudoku) {
        for(int y = 0; y < this.blocks.length;y++ ) {
            for(int x = 0; x < this.blocks[0].length;x++) {
                this.blocks[y][x] = new SudokuSnapshotBlock(this,sudoku.getBlocks()[y][x]);
            }
        }

        matchFieldsByCoords(actors).forEach(s -> s.setIsActor(true));
        matchFieldsByCoords(reactors).forEach(s -> s.setIsReactor(true));
        this.message = message;
    }

    private List<SudokuSnapshotField> matchFieldsByCoords(List<? extends AbstractSudokuField> reference) {
        return Arrays.stream(this.blocks)
                .flatMap(Arrays::stream)
                .map(SudokuSnapshotBlock::getFields)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .filter(s -> {
                    return reference.stream().anyMatch(ss -> ss.equalsCoordinates(s.getBlock().getY(), s.getBlock().getX(), s.getY(), s.getX()));
                })
                .collect(Collectors.toList());
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected Class<SudokuSnapshotField> getFieldClass() {
        return SudokuSnapshotField.class;
    }

    @Override
    protected Class<SudokuSnapshotBlock> getBlockClass() {
        return SudokuSnapshotBlock.class;
    }

    @Override
    protected SudokuSnapshotBlock createSudokuBlock(int y, int x) {
        return new SudokuSnapshotBlock(this,x,y,this.xBlockDim,this.yBlockDim);
    }


}
