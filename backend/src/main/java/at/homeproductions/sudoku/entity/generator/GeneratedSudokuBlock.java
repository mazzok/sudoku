package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudokuBlock;

public class GeneratedSudokuBlock extends AbstractSudokuBlock<GeneratedSudokuField, GeneratedSudoku>{

    public GeneratedSudokuBlock() {
        super();
    }

    public GeneratedSudokuBlock(GeneratedSudoku sudoku, int x, int y, int xDim, int yDim) {
        super(sudoku,x,y,xDim,yDim);
    }

    protected GeneratedSudokuField createSudokuField(int fieldY, int fieldX) {
        return new GeneratedSudokuField(this,fieldX,fieldY);
    }
}
