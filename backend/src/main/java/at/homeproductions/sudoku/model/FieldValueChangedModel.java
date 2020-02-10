package at.homeproductions.sudoku.model;

public class FieldValueChangedModel {

    int blockX;

    int blockY;

    int fieldX;

    int fieldY;

    int value;

    SudokuModel sudokuModel;


    public int getBlockX() {
        return blockX;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

    public int getFieldX() {
        return fieldX;
    }

    public void setFieldX(int fieldX) {
        this.fieldX = fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setFieldY(int fieldY) {
        this.fieldY = fieldY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SudokuModel getSudokuModel() {
        return sudokuModel;
    }

    public void setSudokuModel(SudokuModel sudokuModel) {
        this.sudokuModel = sudokuModel;
    }
}
