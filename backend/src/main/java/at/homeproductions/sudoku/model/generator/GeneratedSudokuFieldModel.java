package at.homeproductions.sudoku.model.generator;

import at.homeproductions.sudoku.entity.PossibleValue;
import at.homeproductions.sudoku.model.PossibleValueModel;

import java.util.ArrayList;
import java.util.List;

public class GeneratedSudokuFieldModel {

    protected Integer value;

    protected int x;
    protected int y;

    protected boolean isSorted;

    public boolean isSorted() {
        return isSorted;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
}
