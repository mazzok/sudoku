package at.homeproductions.sudoku.model;

import at.homeproductions.sudoku.entity.PossibleValue;

import java.util.List;

public class SudokuFieldModel {

    private int x;
    private int y;
    private Integer value;
    private List<PossibleValueModel> possibleValues;

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

    public List<PossibleValueModel> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<PossibleValueModel> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
