package at.homeproductions.sudoku.entity;

public class SudokuSnapshotBlock extends SudokuBlock{
    public SudokuSnapshotBlock(SudokuBlock sudokuBlock) {
        copy(sudokuBlock);
    }

    private void copy(SudokuBlock sudokuBlock) {
        for(int y = 0; y < this.getYDim();y++ ) {
            for(int x = 0; x < getXDim();x++) {
                this.fields[y][x] = new SudokuSnapshotField(sudokuBlock.fields[y][x]);
            }
        }
    }

    @Override
    protected SudokuField[][] createFieldArray(int xDim, int yDim) {
        return new SudokuSnapshotField[xDim][yDim];
    }

    @Override
    protected SudokuSnapshotField createSudokuField(int fieldY, int fieldX) {
        return new SudokuSnapshotField(this, fieldY, fieldX);
    }
}
