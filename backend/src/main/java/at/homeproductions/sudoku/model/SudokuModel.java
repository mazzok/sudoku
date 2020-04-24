package at.homeproductions.sudoku.model;

import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotModel;

import java.util.List;

public class SudokuModel {

    private int xDim;

    private int yDim;

    private List<SudokuBlockModel> sudokuBlocks;

    private List<SudokuSnapshotModel> snapshots;

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

    public List<SudokuBlockModel> getSudokuBlocks() {
        return sudokuBlocks;
    }

    public void setSudokuBlocks(List<SudokuBlockModel> sudokuBlocks) {
        this.sudokuBlocks = sudokuBlocks;
    }

    public List<SudokuSnapshotModel> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<SudokuSnapshotModel> snapshots) {
        this.snapshots = snapshots;
    }
}
