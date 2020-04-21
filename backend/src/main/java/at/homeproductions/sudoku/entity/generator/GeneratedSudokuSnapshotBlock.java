package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudokuBlock;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshot;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotField;

public class GeneratedSudokuSnapshotBlock extends AbstractSudokuBlock<  GeneratedSudokuSnapshotField,GeneratedSudokuSnapshot> {
    public GeneratedSudokuSnapshotBlock() {
        super();
    }

    public GeneratedSudokuSnapshotBlock(GeneratedSudokuSnapshot sudoku, int x, int y, int xDim, int yDim) {
        super(sudoku,x,y,xDim,yDim);
    }

    public GeneratedSudokuSnapshotBlock(GeneratedSudokuSnapshot generatedSudokuSnapshot, AbstractSudokuBlock sudokuBlock) {
        this.sudoku = generatedSudokuSnapshot;
        copy(sudokuBlock);
    }

    private void copy(AbstractSudokuBlock sudokuBlock) {
        this.xDim = sudokuBlock.getXDim();
        this.yDim = sudokuBlock.getYDim();
        this.x = sudokuBlock.getX();
        this.y = sudokuBlock.getY();
        this.fields = createFieldArray(yDim,xDim);
        for(int y = 0; y < this.getYDim();y++ ) {
            for(int x = 0; x < getXDim();x++) {
                this.fields[y][x] = new GeneratedSudokuSnapshotField(this, sudokuBlock.getFields()[y][x]);
            }
        }
    }

    @Override
    protected GeneratedSudokuSnapshotField createSudokuField(int fieldY, int fieldX) {
        return new GeneratedSudokuSnapshotField(this, fieldY, fieldX);
    }


}
