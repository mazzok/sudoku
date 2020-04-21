package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudoku;
import at.homeproductions.sudoku.entity.AbstractSudokuField;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotBlock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneratedSudokuSnapshot extends AbstractSudoku<GeneratedSudokuSnapshotField, GeneratedSudokuSnapshotBlock, GeneratedSudokuSnapshot> {
    private String message;

    public GeneratedSudokuSnapshot(){
        super();
    }
    public GeneratedSudokuSnapshot(String message, AbstractSudoku sudoku) {
        for(int y = 0; y < this.blocks.length;y++ ) {
            for(int x = 0; x < this.blocks[0].length;x++) {
                this.blocks[y][x] = new GeneratedSudokuSnapshotBlock(this,sudoku.getBlocks()[y][x]);
            }
        }
        this.message = message;
    }

    public void setFieldsInColumnSelection(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setInColumnSelection(true));
    }

    public void setFieldsInRowSelection(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setInRowSelection(true));
    }

    public void setFieldsInBlockSelection(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setInBlockSelection(true));
    }

    public void setFieldsInCurrentSelection(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setCurrentlyInSelection(true));
    }

    public void setFreeFields(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setFreeField(true));
    }

    public void setAdjacentFields(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setAdjacent(true));
    }

    public void setDuplicateFields(List<GeneratedSudokuField> fields) {
        matchFieldsByCoords(fields).forEach(s -> s.setDuplicate(true));
    }



    private List<GeneratedSudokuSnapshotField> matchFieldsByCoords(List<? extends AbstractSudokuField> reference) {
        return Arrays.stream(this.blocks)
                .flatMap(Arrays::stream)
                .map(GeneratedSudokuSnapshotBlock::getFields)
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
    protected Class<GeneratedSudokuSnapshotField> getFieldClass() {
        return GeneratedSudokuSnapshotField.class;
    }

    @Override
    protected Class<GeneratedSudokuSnapshotBlock> getBlockClass() {
        return GeneratedSudokuSnapshotBlock.class;
    }

    @Override
    protected GeneratedSudokuSnapshotBlock createSudokuBlock(int y, int x) {
        return new GeneratedSudokuSnapshotBlock(this,x,y,this.xBlockDim,this.yBlockDim);
    }


}
