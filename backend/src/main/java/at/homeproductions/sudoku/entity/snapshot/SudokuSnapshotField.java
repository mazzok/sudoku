package at.homeproductions.sudoku.entity.snapshot;

import at.homeproductions.sudoku.entity.AbstractSudokuField;
import at.homeproductions.sudoku.entity.PossibleValue;
import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.entity.SudokuField;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SudokuSnapshotField extends AbstractSudokuField<SudokuSnapshotBlock> {
    private boolean isReactor;
    private boolean isActor;

    public SudokuSnapshotField() {
        super();
    }

    public SudokuSnapshotField(SudokuSnapshotBlock block, int x, int y, Integer value) {
        super(block, x, y, value);
    }

    public SudokuSnapshotField(SudokuSnapshotBlock sudokuTrailBlock, int fieldY, int fieldX) {
        super(sudokuTrailBlock,fieldY,fieldX);
    }

    public SudokuSnapshotField(SudokuSnapshotBlock block, SudokuField sudokuField) {
        this.block = block;
        copy(sudokuField);
    }

//    public static SudokuSnapshotField asActor(SudokuField s) {
//        SudokuSnapshotField snapshotField = new SudokuSnapshotField(s);
//        snapshotField.setIsActor(true);
//        snapshotField.setIsReactor(false);
//        return snapshotField;
//    }
//
//
//    public static SudokuSnapshotField asReactor(SudokuField s) {
//        SudokuSnapshotField snapshotField = new SudokuSnapshotField(s);
//        snapshotField.setIsActor(false);
//        snapshotField.setIsReactor(true);
//        return snapshotField;
//    }

    private void copy(SudokuField sudokuField) {
        this.value = sudokuField.getValue();
        this.x = sudokuField.getX();
        this.y = sudokuField.getY();
        this.possibleValues = sudokuField.getPossibleValues().stream().map(this::copy).collect(Collectors.toList());
    }

    private PossibleValue copy(PossibleValue other) {
        PossibleValue copyTo = new PossibleValue();
        copyTo.setIsHidden(other.getIsHidden());
        copyTo.setValue(other.getValue());
        return copyTo;
    }


    public boolean getIsActor() {
        return this.isActor;
    }

    public void setIsActor(boolean b) {
        this.isActor = b;
    }

    public void setIsReactor(boolean b) {
        this.isReactor = b;
    }

    public boolean getIsReactor() {
        return isReactor;
    }

}
