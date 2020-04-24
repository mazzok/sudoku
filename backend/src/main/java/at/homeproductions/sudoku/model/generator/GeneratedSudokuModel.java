package at.homeproductions.sudoku.model.generator;

import at.homeproductions.sudoku.model.SudokuBlockModel;
import at.homeproductions.sudoku.model.generator.snapshot.GeneratedSudokuSnapshotModel;
import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotModel;

import java.util.List;

public class GeneratedSudokuModel {

    private int xDim;

    private int yDim;

    private List<GeneratedSudokuBlockModel> generatedSudokuBlocks;

    private List<GeneratedSudokuSnapshotModel> generatedSudokuSnapshots;

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

    public List<GeneratedSudokuBlockModel> getGeneratedSudokuBlocks() {
        return generatedSudokuBlocks;
    }

    public void setGeneratedSudokuBlocks(List<GeneratedSudokuBlockModel> generatedSudokuBlocks) {
        this.generatedSudokuBlocks = generatedSudokuBlocks;
    }

    public List<GeneratedSudokuSnapshotModel> getGeneratedSudokuSnapshots() {
        return generatedSudokuSnapshots;
    }

    public void setGeneratedSudokuSnapshots(List<GeneratedSudokuSnapshotModel> generatedSudokuSnapshots) {
        this.generatedSudokuSnapshots = generatedSudokuSnapshots;
    }
}
