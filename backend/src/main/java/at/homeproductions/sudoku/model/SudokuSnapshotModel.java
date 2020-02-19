package at.homeproductions.sudoku.model;

import java.util.List;

public class SudokuSnapshotModel {

    private int xDim;

    private int yDim;

    private String message;

    private List<SudokuSnapshotBlockModel> sudokuBlocks;

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

    public List<SudokuSnapshotBlockModel> getSudokuBlocks() {
        return sudokuBlocks;
    }

    public void setSudokuBlocks(List<SudokuSnapshotBlockModel> sudokuBlocks) {
        this.sudokuBlocks = sudokuBlocks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
