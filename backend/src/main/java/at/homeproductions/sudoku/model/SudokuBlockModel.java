package at.homeproductions.sudoku.model;

import java.util.List;

public class SudokuBlockModel {

    private int x;

    private int y;

    private List<SudokuFieldModel> sudokuFields;

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

    public List<SudokuFieldModel> getSudokuFields() {
        return sudokuFields;
    }

    public void setSudokuFields(List<SudokuFieldModel> sudokuFields) {
        this.sudokuFields = sudokuFields;
    }
}
