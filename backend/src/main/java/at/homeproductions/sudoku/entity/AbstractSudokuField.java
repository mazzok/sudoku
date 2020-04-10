package at.homeproductions.sudoku.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AbstractSudokuField<B extends AbstractSudokuBlock> {

    protected B block;
    protected Integer value;
    protected boolean isValueReserved;
    protected List<PossibleValue> possibleValues = new ArrayList<>();

    protected int x;
    protected int y;

    public AbstractSudokuField() {
        super();
    }


    public AbstractSudokuField(B block, int x, int y, Integer value) {
        this(block,x,y);
        this.value = value;
        if (this.value != null) {
            this.possibleValues.clear();
        }
    }


    public AbstractSudokuField(B block, int x, int y) {
        super();
        this.block = block;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "field ["+getX()+","+getY()+"] " +
                "in block ["+getBlock().getX()+","+getBlock().getY()+"] with value: " + String.valueOf(this.getValue());
    }

    public B getBlock() {
        return this.block;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setBlock(B block) {
        this.block = block;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


     public void copy(SudokuField sudokuField) {
        this.value = sudokuField.getValue();
        this.x = sudokuField.getX();
        this.y = sudokuField.getY();
        this.possibleValues = sudokuField.getPossibleValues().stream().map(PossibleValue::copy).collect(Collectors.toList());
        this.isValueReserved = sudokuField.getIsValueReserved();
    }


    public boolean equalsCoordinates(int blockY, int blockX, int fieldY, int fieldX) {
        return blockY == block.y && blockX == block.x && fieldY == y && fieldX == x;
    }

    public Integer getValue() {
        return value;
    }

    public List<PossibleValue> getPossibleValues() {
        return this.possibleValues;
    }

    public void setPossibleValues(List<PossibleValue> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public boolean getIsValueReserved() {
        return isValueReserved;
    }

    public void setIsValueReserved(boolean valueReserved) {
        isValueReserved = valueReserved;
    }
}
