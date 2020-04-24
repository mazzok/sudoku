package at.homeproductions.sudoku.model.generator.snapshot;

import at.homeproductions.sudoku.model.PossibleValueModel;

import java.util.List;

public class GeneratedSudokuSnapshotFieldModel {

    private int x;
    private int y;
    private Integer value;

    private boolean isInColumnSelection;
    private boolean isInRowSelection;
    private boolean isInBlockSelection;
    private boolean isCurrentlyInSelection;
    private boolean isSorted;

    private boolean isFreeField;
    private boolean isAdjacent;
    private boolean isDuplicate;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    public boolean isSorted() {
        return isSorted;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
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
