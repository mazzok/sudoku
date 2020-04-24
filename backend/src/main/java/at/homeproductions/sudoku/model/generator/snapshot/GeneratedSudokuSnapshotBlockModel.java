package at.homeproductions.sudoku.model.generator.snapshot;

import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotFieldModel;

import java.util.List;

public class GeneratedSudokuSnapshotBlockModel {

    private int xDim;

    private int yDim;

    private int x;

    private int y;

    private List<GeneratedSudokuSnapshotFieldModel> generatedSudokuFields;

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

    public List<GeneratedSudokuSnapshotFieldModel> getGeneratedSudokuFields() {
        return generatedSudokuFields;
    }

    public void setGeneratedSudokuFields(List<GeneratedSudokuSnapshotFieldModel> generatedSudokuFields) {
        this.generatedSudokuFields = generatedSudokuFields;
    }
}
