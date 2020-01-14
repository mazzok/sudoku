package at.homeproductions.sudoku.model;

import java.util.List;

public class SudokuModel {

    private int xDim;

    private int yDim;

    private List<SudokuBlockModel> sudokuBlocks;

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
}
