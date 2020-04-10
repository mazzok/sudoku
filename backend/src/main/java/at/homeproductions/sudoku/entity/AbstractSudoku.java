package at.homeproductions.sudoku.entity;

import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshot;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotField;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractSudoku<F extends AbstractSudokuField<B>, B extends AbstractSudokuBlock<F, S>, S extends AbstractSudoku<F,B,S>> {


    protected int yBlockDim;
    protected int xBlockDim;
    protected B[][] blocks;
    protected int xBlocks;
    protected int yBlocks;

    public AbstractSudoku() {
        init();
    }

    public String toString() {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < this.yBlockDim*this.yBlocks;i++) {
            b.append(Arrays.stream(getRow(i))
                    .map(f -> f.getValue() != null ? String.valueOf(f.getValue()) : "["+f.getPossibleValues().stream().map(k -> String.valueOf(k.getValue()) + (k.getIsHidden() == true ? "(h)" : "")).collect(Collectors.joining(","))+"]")
                    .collect(Collectors.joining(";")) + System.getProperty("line.separator"));
        }
        return b.toString();
    }

    protected void init() {
        this.xBlocks = 3;
        this.yBlocks = 3;

        this.xBlockDim = 3;
        this.yBlockDim = 3;

        this.blocks = (B[][]) Array.newInstance(getBlockClass(), xBlocks, yBlocks);
        for(int y = 0; y < yBlocks; y++) {
            for (int x = 0; x < xBlocks; x++) {
                this.blocks[y][x] = createSudokuBlock(y, x);
            }
        }
    }

    protected abstract B createSudokuBlock(int y, int x);

    public F[] getRow(int rowNum) {
        F[] out = (F[]) Array.newInstance(getFieldClass(), this.xBlockDim*this.xBlocks);
        int index = 0;
        int blockYCoord = rowNum / this.xBlockDim;
        for (int i = 0; i < this.xBlocks; i++) {
            B block = this.blocks[blockYCoord][i];
            for (int j = 0; j < block.getXDim();j++) {
                F field = block.getField(j,rowNum % this.xBlockDim);
                out[index++] = field;
            }
        }
        return out;
    }

    protected abstract Class<F> getFieldClass();

    protected abstract Class<B> getBlockClass();


    public F[] getColumn(int colNum) {
        F[] out = (F[]) Array.newInstance(getFieldClass(), this.xBlockDim*this.xBlocks);
        int index = 0;
        int blockYCoord = colNum / this.yBlockDim;
        for (int i = 0; i < this.yBlocks; i++) {
            B block = this.blocks[i][blockYCoord];
            for (int j = 0; j < block.getYDim();j++) {
                F field = block.getField(colNum % this.yBlockDim, j);
                out[index++] = field;
            }
        }
        return out;
    }

    public int getRowIndex(F f) {
        return f.getBlock().getY()*this.yBlockDim + f.getY();
    }

    public int getColIndex(F f) {
        return f.getBlock().getX()*this.xBlockDim + f.getX();
    }

    public int getyBlockDim() {
        return yBlockDim;
    }

    public int getxBlockDim() {
        return xBlockDim;
    }

    public void setyBlockDim(int yBlockDim) {
        this.yBlockDim = yBlockDim;
    }

    public void setxBlockDim(int xBlockDim) {
        this.xBlockDim = xBlockDim;
    }

    public B[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(B[][] blocks) {
        this.blocks = blocks;
    }

    public int getxBlocks() {
        return xBlocks;
    }

    public void setxBlocks(int xBlocks) {
        this.xBlocks = xBlocks;
    }

    public int getyBlocks() {
        return yBlocks;
    }

    public void setyBlocks(int yBlocks) {
        this.yBlocks = yBlocks;
    }

    public F getField(int blockX, int blockY, int x, int y) {
        return this.blocks[blockY][blockX].getField(x, y);
    }
}
