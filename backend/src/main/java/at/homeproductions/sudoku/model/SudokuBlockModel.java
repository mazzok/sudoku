package at.homeproductions.sudoku.model;

import java.util.List;

public class SudokuBlockModel {

    private int xDim;

    private int yDim;

    private int x;

    private int y;

    private List<SudokuFieldModel> sudokuFields;

    public int getX() {
        return x;
    }

    public int getxDim() {
        return xDim;
    }

    public void setxDim(int xDim) {
        this.xDim = xDim;
    }

    public int getyDim() {
        return yDim;
    }

    public void setyDim(int yDim) {
        this.yDim = yDim;
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
