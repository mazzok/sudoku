package at.homeproductions.sudoku.entity.snapshot;

import at.homeproductions.sudoku.entity.AbstractSudokuBlock;
import at.homeproductions.sudoku.entity.SudokuBlock;

public class SudokuSnapshotBlock extends AbstractSudokuBlock<SudokuSnapshotField,SudokuSnapshot> {
    public SudokuSnapshotBlock() {
        super();
    }

    public SudokuSnapshotBlock(SudokuSnapshot sudoku, int x, int y, int xDim, int yDim) {
        super(sudoku,x,y,xDim,yDim);
    }

    public SudokuSnapshotBlock(SudokuSnapshot sudokuSnapshot, SudokuBlock sudokuBlock) {
        this.sudoku = sudokuSnapshot;
        copy(sudokuBlock);
    }

    private void copy(SudokuBlock sudokuBlock) {
        this.xDim = sudokuBlock.getXDim();
        this.yDim = sudokuBlock.getYDim();
        this.x = sudokuBlock.getX();
        this.y = sudokuBlock.getY();
        this.fields = createFieldArray(yDim,xDim);
        for(int y = 0; y < this.getYDim();y++ ) {
            for(int x = 0; x < getXDim();x++) {
                this.fields[y][x] = new SudokuSnapshotField(this, sudokuBlock.getFields()[y][x]);
            }
        }
    }

    @Override
    protected SudokuSnapshotField createSudokuField(int fieldY, int fieldX) {
        return new SudokuSnapshotField(this, fieldY, fieldX);
    }


}
