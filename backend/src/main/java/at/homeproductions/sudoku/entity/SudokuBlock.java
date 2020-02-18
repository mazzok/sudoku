package at.homeproductions.sudoku.entity;

public class SudokuBlock extends AbstractSudokuBlock<SudokuField, Sudoku>{

    public SudokuBlock() {
        super();
    }

    public SudokuBlock(Sudoku sudoku, int x, int y, int xDim, int yDim) {
        super(sudoku,x,y,xDim,yDim);
    }

    protected SudokuField createSudokuField(int fieldY, int fieldX) {
        return new SudokuField(this,fieldX,fieldY);
    }
}
