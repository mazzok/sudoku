package at.homeproductions.sudoku.entity;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest {

    private Sudoku sudoku;

    @Before
    public void setup() {
        this.sudoku = Sudoku.getDefaulSudoku();
        this.sudoku.calculateCandidates();
    }

    @Test
    public void testContainsSinglePossibleValue() {
        SudokuField f = this.sudoku.getField(2,0,1,0);
        this.sudoku.containsSinglePossibleValue(sudoku.getColumn(sudoku.getColIndex(f)));
    }
}
