package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SudokuField {

    protected SudokuBlock block;
    protected Integer value;

    protected List<Integer> possibleValues = new ArrayList<>();
    protected int x;
    protected int y;

    protected SudokuField() {
        super();
    }


    public SudokuField(SudokuBlock block, int x, int y, Integer value) {
        this(block,x,y);
        this.value = value;

        if (this.value != null) {
            this.possibleValues.clear();
        }
//        else {
//            this.possibleValues.remove(value);
//        }
    }


    public SudokuField(SudokuBlock block, int x, int y) {
        super();
        this.block = block;
        this.x = x;
        this.y = y;
        initPossibleValues();

    }


    private void initPossibleValues() {
        this.possibleValues = IntStream.rangeClosed(1,9).boxed().collect(Collectors.toList());
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "field ["+getX()+","+getY()+"] " +
                "in block ["+getBlock().getX()+","+getBlock().getY()+"] with value: " + String.valueOf(this.getValue());
    }

    public List<Integer> getPossibleValues() {
        return this.possibleValues;
    }

    public SudokuBlock getBlock() {
        return this.block;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setValue(Integer value, SudokuField[] row, SudokuField[] column) {
        this.value = value;
        System.out.println("Setting Value to "+value);
        List<SudokuField> reactors = Stream.concat( Stream.of(this.block.getFields()).flatMap(s -> Stream.of(s)) ,
                Stream.concat(Arrays.stream(row),Arrays.stream(column)))
                .distinct().collect(Collectors.toList());

        reactors.forEach(sf -> sf.getPossibleValues().remove(value));
        this.getBlock().getSudoku().logSolutionTrailStep(String.format("Setting Field  %s Value to %s, removing the valu from columns and rows", this, value), Collections.singletonList(this), reactors );

        this.getPossibleValues().clear();
    }

    public boolean equalsCoordinates(int blockY, int blockX, int fieldY, int fieldX) {
        return blockY == block.y && blockX == block.x && fieldY == y && fieldX == x;
    }
}
