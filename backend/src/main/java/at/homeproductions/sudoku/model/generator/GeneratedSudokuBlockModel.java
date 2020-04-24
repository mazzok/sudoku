package at.homeproductions.sudoku.model.generator;

import at.homeproductions.sudoku.model.SudokuFieldModel;

import java.util.List;

public class GeneratedSudokuBlockModel {

    private int xDim;

    private int yDim;

    private int x;

    private int y;

    private List<GeneratedSudokuFieldModel> generatedSudokuFields;

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

    public List<GeneratedSudokuFieldModel> getGeneratedSudokuFields() {
        return generatedSudokuFields;
    }

    public void setGeneratedSudokuFields(List<GeneratedSudokuFieldModel> generatedSudokuFields) {
        this.generatedSudokuFields = generatedSudokuFields;
    }
}
