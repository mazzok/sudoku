package at.homeproductions.sudoku.model;

import java.util.List;

public class SudokuSnapshotFieldModel {

    private boolean isReactor;
    private boolean isActor;
    private int x;
    private int y;
    private Integer value;
    private List<PossibleValueModel> possibleValues;
    private boolean isValueReserved;

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

    public boolean getIsReactor() {
        return isReactor;
    }

    public void setReactor(boolean reactor) {
        isReactor = reactor;
    }

    public boolean getIsActor() {
        return isActor;
    }

    public void setIsActor(boolean actor) {
        isActor = actor;
    }

    public void setIsReactor(boolean isReactor) {
        this.isReactor = isReactor;
    }

    public List<PossibleValueModel> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<PossibleValueModel> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public boolean getIsValueReserved() {
        return isValueReserved;
    }

    public void setIsValueReserved(boolean valueReserved) {
        isValueReserved = valueReserved;
    }
}
