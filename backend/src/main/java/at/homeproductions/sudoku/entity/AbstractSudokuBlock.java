package at.homeproductions.sudoku.entity;

import java.lang.reflect.Array;

public abstract class AbstractSudokuBlock<F extends AbstractSudokuField, S extends AbstractSudoku> {


    protected F[][] fields;
    protected S sudoku;
    protected int x;
    protected int y;
    protected int xDim;
    protected int yDim;

    public AbstractSudokuBlock() {
        super();
    }

    public AbstractSudokuBlock(S sudoku, int x, int y, int xDim, int yDim) {
        this.sudoku = sudoku;
        this.x = x;
        this.y = y;
        this.xDim = xDim;
        this.yDim = yDim;

        this.fields = createFieldArray(xDim, yDim);
        for(int fieldY = 0; fieldY < yDim; fieldY++) {
            for (int fieldX = 0; fieldX < xDim; fieldX++) {
                this.fields[fieldY][fieldX] = createSudokuField(fieldY, fieldX);
            }
        }
    }


    public void addField(int x, int y, F sudokuField) {
        this.fields[y][x] = sudokuField;
    }

    protected abstract F createSudokuField(int fieldY, int fieldX);

    protected F[][] createFieldArray(int xDim, int yDim) {
        return (F[][]) Array.newInstance(this.sudoku.getFieldClass(), xDim, yDim);
    };

    public int getYDim() {
        return this.yDim;
    }

    public F[][] getFields() {
        return this.fields;
    }

    public int getXDim() {
        return  this.xDim;
    }


    public F getField(int x, int y) {
        return this.fields[y][x];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public S getSudoku() {
        return sudoku;
    }

    public void setSudoku(S sudoku) {
        this.sudoku = sudoku;
    }

    public void setFields(F[][] fields) {
        this.fields = fields;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setxDim(int xDim) {
        this.xDim = xDim;
    }

    public void setyDim(int yDim) {
        this.yDim = yDim;
    }
}
