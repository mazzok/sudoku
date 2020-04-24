package at.homeproductions.sudoku.model.generator.snapshot;

import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotBlockModel;

import java.util.List;

public class GeneratedSudokuSnapshotModel {

    private int xDim;

    private int yDim;

    private String message;

    private List<GeneratedSudokuSnapshotBlockModel> generatedSudokuBlocks;

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

    public List<GeneratedSudokuSnapshotBlockModel> getGeneratedSudokuBlocks() {
        return generatedSudokuBlocks;
    }

    public void setGeneratedSudokuBlocks(List<GeneratedSudokuSnapshotBlockModel> generatedSudokuBlocks) {
        this.generatedSudokuBlocks = generatedSudokuBlocks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
