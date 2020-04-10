package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static at.homeproductions.sudoku.entity.SudokuField.DEFAULT_MAX_VALUE;
import static at.homeproductions.sudoku.entity.SudokuField.DEFAULT_MIN_VALUE;

public class GeneratedSudoku extends AbstractSudoku<GeneratedSudokuField, GeneratedSudokuBlock, GeneratedSudoku> {

    public GeneratedSudoku() {
        super();
        randomize();
    }

    @Override
    protected GeneratedSudokuBlock createSudokuBlock(int y, int x) {
        return new GeneratedSudokuBlock(this,x,y,this.xBlockDim,this.yBlockDim);
    }

    @Override
    protected Class<GeneratedSudokuField> getFieldClass() {
        return GeneratedSudokuField.class;
    }

    @Override
    protected Class<GeneratedSudokuBlock> getBlockClass() {
        return GeneratedSudokuBlock.class;
    }



    public GeneratedSudokuField[] getRow(int index, int start) {
        GeneratedSudokuField[] row = super.getRow(index);
        return  IntStream.range(start, row.length )
                .mapToObj(i -> row[i])
                .toArray(GeneratedSudokuField[]::new);
    }

    public GeneratedSudokuField[] getColumn(int colNum, int start) {
        GeneratedSudokuField[] column = super.getColumn(colNum);
        return IntStream.range(colNum, column.length)
                .mapToObj(i -> column[i])
                .toArray(GeneratedSudokuField[]::new);
    }

    private void randomize() {
        for (int y = 0; y < getyBlockDim();y++) {
            for (int x = 0; x < getxBlockDim();x++) {
                List<Integer> possibleValues = Arrays.asList(1,2,3,4,5,6,7,8,9);
                Collections.shuffle(possibleValues);
                GeneratedSudokuBlock b = getBlocks()[x][y];
                for (int fy = 0; fy < b.getYDim();fy++) {
                    for (int fx = 0; fx < b.getXDim();fx++) {
                        GeneratedSudokuField f = b.getField(fx,fy);
                        f.setValue(possibleValues.get(fy*b.getXDim() + fx));
                    }
                }
            }
        }
    }

    @Override
    protected void init() {
        super.init();
    }


    boolean checkValidity(int value, GeneratedSudokuField sudokuField) {
        return checkBlockSum(value,sudokuField)
                && checkRowSum(value,sudokuField)
                && checkColumnSum(value,sudokuField)
                && checkNoDuplicatesRow(value,sudokuField)
                && checkNoDuplicatesColumn(value,sudokuField)
                && checkNoDuplicatesBlock(value,sudokuField);
    }

    private boolean checkNoDuplicatesBlock(int value, GeneratedSudokuField sudokuField) {
        GeneratedSudokuBlock block = sudokuField.getBlock();
        List<Integer> mappedValues = Arrays.stream(block.getFields()).flatMap(Arrays::stream).filter( s -> s.getValue() != null).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        mappedValues.add(value);
        long fullCount = mappedValues.stream().count();
        long distinctCount = mappedValues.stream().distinct().count();
        return distinctCount == fullCount;
    }

    private boolean checkNoDuplicatesColumn(int value, GeneratedSudokuField sudokuField) {
        GeneratedSudoku sudoku = sudokuField.getBlock().getSudoku();
        GeneratedSudokuField[] column = sudoku.getColumn(sudoku.getColIndex(sudokuField));
        List<Integer> mappedValues  = Arrays.stream(column).filter( s -> s.getValue() != null).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        mappedValues.add(value);
        long fullCount = mappedValues.stream().count();
        long distinctCount = mappedValues.stream().distinct().count();
        return distinctCount == fullCount;
    }

    private boolean checkNoDuplicatesRow(int value, GeneratedSudokuField sudokuField) {
        GeneratedSudoku sudoku = sudokuField.getBlock().getSudoku();
        GeneratedSudokuField[] row = sudoku.getRow(sudoku.getRowIndex(sudokuField));
        List<Integer> mappedValues = Arrays.stream(row).filter( s -> s.getValue() != null).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        mappedValues.add(value);
        long fullCount= mappedValues.stream().count();
        long distinctCount = mappedValues.stream().distinct().count();

        return distinctCount == fullCount;
    }

    private boolean checkColumnSum(int value, GeneratedSudokuField sudokuField) {
        GeneratedSudoku sudoku = sudokuField.getBlock().getSudoku();
        GeneratedSudokuField[] column = sudoku.getColumn(sudoku.getColIndex(sudokuField));
        int maxSum = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).sum();
        int currentColumnSum = Arrays.stream(column).filter( s -> s.getValue() != null)
                .map(GeneratedSudokuField::getValue)
                .mapToInt(Integer::valueOf).sum();
        return currentColumnSum + value <= maxSum;
    }

    private boolean checkRowSum(int value, GeneratedSudokuField sudokuField) {
        GeneratedSudoku sudoku = sudokuField.getBlock().getSudoku();
        GeneratedSudokuField[] row = sudoku.getRow(sudoku.getRowIndex(sudokuField));
        int maxSum = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).sum();
        int currentRowSum = Arrays.stream(row).filter( s -> s.getValue() != null)
                .map(GeneratedSudokuField::getValue)
                .mapToInt(Integer::valueOf).sum();
        return currentRowSum + value <= maxSum;
    }

    private boolean checkBlockSum(int value, GeneratedSudokuField sudokuField) {
        GeneratedSudokuBlock block = sudokuField.getBlock();
        int maxSum = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).sum();
        int currentBlockSum = Arrays.stream(block.getFields()).flatMap(Arrays::stream).filter( s -> s.getValue() != null)
                .map(GeneratedSudokuField::getValue)
                .mapToInt(Integer::valueOf).sum();
        return currentBlockSum + value <= maxSum;
    }
}

