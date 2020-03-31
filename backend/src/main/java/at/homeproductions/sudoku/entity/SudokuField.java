package at.homeproductions.sudoku.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.singletonList;

public class SudokuField extends AbstractSudokuField<SudokuBlock>{

    protected boolean isInitialField;



    public SudokuField() {
        super();
    }


    public SudokuField(SudokuBlock block, int x, int y, Integer value) {
        super(block,x,y,value);
    }


    public SudokuField(SudokuBlock block, int x, int y) {
        super(block,x,y);
        initPossibleValues();
    }


    private void initPossibleValues() {
        this.possibleValues = IntStream.rangeClosed(1,9).boxed().map(PossibleValue::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "field ["+getX()+","+getY()+"] " +
                "in block ["+getBlock().getX()+","+getBlock().getY()+"] with value: " + String.valueOf(this.getValue());
    }

    public boolean getIsInitialField() {
        return isInitialField;
    }

    public void setIsInitialField(boolean initialField) {
        this.isInitialField = initialField;
    }

    public void setValue(Integer value, boolean hideOwnPossibleValues) {
        String message = String.format("Setting Field  %s to %s, removing the value from its block, column and row", this, value);
        this.value = value;
        System.out.println("Setting Value to "+value);
        List<SudokuField> reactors = FieldVincinityCalculator.getPossibleFieldVincinityReactors(this);

        reactors.stream()
                .map(SudokuField::getPossibleValues)
                .forEach(s-> s.stream()
                        .filter(p -> p.getValue()==value)
                        .forEach(p-> p.setIsHidden(true)));
        this.getBlock().getSudoku().logSolutionTrailStep(message, Collections.singletonList(this), reactors);

        if (hideOwnPossibleValues) {
            this.getPossibleValues().forEach(p->p.setIsHidden(true));
        }
    }

}
