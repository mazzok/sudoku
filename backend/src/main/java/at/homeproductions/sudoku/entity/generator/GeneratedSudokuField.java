package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudokuField;
import at.homeproductions.sudoku.entity.PossibleValue;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneratedSudokuField extends AbstractSudokuField<GeneratedSudokuBlock>{

    public static final int DEFAULT_MIN_VALUE = 1;
    public static final int DEFAULT_MAX_VALUE = 9;
    protected boolean isSorted;

    public GeneratedSudokuField() {
        super();
    }


    public GeneratedSudokuField(GeneratedSudokuBlock block, int x, int y, Integer value) {
        super(block,x,y,value);
    }


    public GeneratedSudokuField(GeneratedSudokuBlock block, int x, int y) {
        super(block,x,y);
        initPossibleValues();
    }


    private void initPossibleValues() {
        this.possibleValues = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).boxed().map(PossibleValue::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "field ["+getX()+","+getY()+"] " +
                "in block ["+getBlock().getX()+","+getBlock().getY()+"] with value: " + String.valueOf(this.getValue());
    }


    public boolean isSorted() {
        return isSorted;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
    }
}
