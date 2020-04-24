package at.homeproductions.sudoku.entity.generator;

import at.homeproductions.sudoku.entity.AbstractSudoku;
import at.homeproductions.sudoku.entity.AbstractSudokuBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static at.homeproductions.sudoku.entity.SudokuField.DEFAULT_MAX_VALUE;
import static at.homeproductions.sudoku.entity.SudokuField.DEFAULT_MIN_VALUE;

public class GeneratedSudoku extends AbstractSudoku<GeneratedSudokuField, GeneratedSudokuBlock, GeneratedSudoku> {

    List<GeneratedSudokuSnapshot> generatedSudokuSnapshots = new ArrayList<>();

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
    public String toString() {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < this.yBlockDim*this.yBlocks;i++) {
            b.append(Arrays.stream(getRow(i))
                    .map(f -> String.valueOf(f.getValue()))
                    .collect(Collectors.joining(";"))
                    + System.getProperty("line.separator"));
        }
        return b.toString();
    }

    public boolean isValid() {
        boolean returnV = true;
        List<GeneratedSudokuField> fields = Arrays.stream(this.getBlocks())
                .flatMap(Arrays::stream)
                .map(AbstractSudokuBlock::getFields)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        for (GeneratedSudokuField f : fields) {
            if(checkValidity(f)) {
                continue;
            }
            return false;
        }

        return returnV;
    }

    private boolean checkValidity(GeneratedSudokuField sudokuField) {
        return checkBlockSum(sudokuField)
                && checkRowSum(sudokuField)
                && checkColumnSum(sudokuField)
                && checkNoDuplicatesRow(sudokuField)
                && checkNoDuplicatesColumn(sudokuField)
                && checkNoDuplicatesBlock(sudokuField);
    }

    private boolean checkNoDuplicatesBlock(GeneratedSudokuField sudokuField) {
        GeneratedSudokuBlock block = sudokuField.getBlock();
        List<Integer> mappedValues = Arrays.stream(block.getFields()).flatMap(Arrays::stream).filter( s -> s.getValue() != null).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        long fullCount = mappedValues.stream().count();
        long distinctCount = mappedValues.stream().distinct().count();
        boolean returnV = distinctCount == fullCount;
        if(returnV == false) {
            System.out.println(String.format("%s has duplicates in its Block",sudokuField));
        }
        return returnV;
    }

    private boolean checkNoDuplicatesColumn(int colIndex) {
        GeneratedSudokuField[] column = getColumn(colIndex);
        List<Integer> mappedValues  = Arrays.stream(column).filter( s -> s.getValue() != null).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        long fullCount = mappedValues.stream().count();
        long distinctCount = mappedValues.stream().distinct().count();
        boolean returnV = distinctCount == fullCount;
        if(returnV == false) {
            System.out.println(String.format("Column index %s has duplicates!", colIndex));
        }
        return returnV;
    }

    private boolean checkNoDuplicatesColumn(GeneratedSudokuField sudokuField) {
        return checkNoDuplicatesColumn(getColIndex(sudokuField));
    }

    private boolean checkNoDuplicatesRow(int rowIndex) {
        GeneratedSudokuField[] row = getRow(rowIndex);
        List<Integer> mappedValues = Arrays.stream(row).filter( s -> s.getValue() != null).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        long fullCount= mappedValues.stream().count();
        long distinctCount = mappedValues.stream().distinct().count();
        boolean returnV = distinctCount == fullCount;
        if(returnV == false) {
            System.out.println(String.format("Row index %s has duplicates!", rowIndex));
        }
        return returnV;
    }

    private boolean checkNoDuplicatesRow(GeneratedSudokuField sudokuField) {
        return checkNoDuplicatesRow(getRowIndex(sudokuField));
    }

    private boolean checkColumnSum(int colIndex) {
        GeneratedSudokuField[] column = getColumn(colIndex);
        int maxSum = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).sum();
        int currentColumnSum = Arrays.stream(column).filter( s -> s.getValue() != null)
                .map(GeneratedSudokuField::getValue)
                .mapToInt(Integer::valueOf).sum();

        boolean returnV = currentColumnSum <= maxSum;
        if(returnV == false) {
            System.out.println(String.format("column index %s has invalid column sum", colIndex));
        }
        return returnV;
    }

    private boolean checkColumnSum(GeneratedSudokuField sudokuField) {
        return checkColumnSum(getColIndex(sudokuField));
    }

    private boolean checkRowSum(int rowIndex) {
        GeneratedSudokuField[] row = getRow(rowIndex);
        int maxSum = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).sum();
        int currentRowSum = Arrays.stream(row).filter( s -> s.getValue() != null)
                .map(GeneratedSudokuField::getValue)
                .mapToInt(Integer::valueOf).sum();
        boolean returnV = currentRowSum <= maxSum;
        if(returnV == false) {
            System.out.println(String.format("RowIndex %s has invalid row sum",rowIndex));
        }
        return returnV;
    }

    private boolean checkRowSum(GeneratedSudokuField sudokuField) {
        return checkRowSum(getRowIndex(sudokuField));
    }

    private boolean checkBlockSum(GeneratedSudokuField sudokuField) {
        GeneratedSudokuBlock block = sudokuField.getBlock();
        int maxSum = IntStream.rangeClosed(DEFAULT_MIN_VALUE,DEFAULT_MAX_VALUE).sum();
        int currentBlockSum = Arrays.stream(block.getFields()).flatMap(Arrays::stream).filter( s -> s.getValue() != null)
                .map(GeneratedSudokuField::getValue)
                .mapToInt(Integer::valueOf).sum();
        boolean returnV = currentBlockSum <= maxSum;
        if(returnV == false) {
            System.out.println(String.format("%s has invalid block sum",sudokuField));
        }
        return returnV;
    }

    public boolean isNotInColOrRow(GeneratedSudokuField s, int rowOrColumnNum) {
        return !(this.getColIndex(s) == rowOrColumnNum || this.getRowIndex(s) == rowOrColumnNum);
    }

    public void addSnaptshot(GeneratedSudokuSnapshot generatedSudokuSnapshot) {
        this.generatedSudokuSnapshots.add(generatedSudokuSnapshot);
    }

    public GeneratedSudokuSnapshot getCurrentSnapshot() {
        return this.generatedSudokuSnapshots.get(this.generatedSudokuSnapshots.size()-1);
    }

    public List<GeneratedSudokuSnapshot> getGeneratedSudokuSnapshots() {
        return generatedSudokuSnapshots;
    }

    public void setGeneratedSudokuSnapshots(List<GeneratedSudokuSnapshot> generatedSudokuSnapshots) {
        this.generatedSudokuSnapshots = generatedSudokuSnapshots;
    }
}

