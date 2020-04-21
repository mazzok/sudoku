package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudokuField;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotBlock;

public class GeneratedSudokuSnapshotField extends AbstractSudokuField<GeneratedSudokuSnapshotBlock> {
    private boolean isInColumnSelection;
    private boolean isInRowSelection;
    private boolean isInBlockSelection;
    private boolean isCurrentlyInSelection;

    private boolean isFreeField;
    private boolean isAdjacent;
    private boolean isDuplicate;


    public GeneratedSudokuSnapshotField() {
        super();
    }

    public GeneratedSudokuSnapshotField(GeneratedSudokuSnapshotBlock block, int x, int y, Integer value) {
        super(block, x, y, value);
    }

    public GeneratedSudokuSnapshotField(GeneratedSudokuSnapshotBlock sudokuTrailBlock, int fieldY, int fieldX) {
        super(sudokuTrailBlock,fieldY,fieldX);
    }

    public GeneratedSudokuSnapshotField(GeneratedSudokuSnapshotBlock block, AbstractSudokuField sudokuField) {
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


    public boolean isInColumnSelection() {
        return isInColumnSelection;
    }

    public void setInColumnSelection(boolean inColumnSelection) {
        isInColumnSelection = inColumnSelection;
    }

    public boolean isInRowSelection() {
        return isInRowSelection;
    }

    public void setInRowSelection(boolean inRowSelection) {
        isInRowSelection = inRowSelection;
    }

    public boolean isInBlockSelection() {
        return isInBlockSelection;
    }

    public void setInBlockSelection(boolean inBlockSelection) {
        isInBlockSelection = inBlockSelection;
    }

    public boolean isCurrentlyInSelection() {
        return isCurrentlyInSelection;
    }

    public void setCurrentlyInSelection(boolean currentlyInSelection) {
        isCurrentlyInSelection = currentlyInSelection;
    }

    public boolean isFreeField() {
        return isFreeField;
    }

    public void setFreeField(boolean freeField) {
        isFreeField = freeField;
    }

    public boolean isAdjacent() {
        return isAdjacent;
    }

    public void setAdjacent(boolean adjacent) {
        isAdjacent = adjacent;
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }

    public void setDuplicate(boolean duplicate) {
        isDuplicate = duplicate;
    }
}
