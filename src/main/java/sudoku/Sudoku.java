package sudoku;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sudoku {


    private final int yBlockDim;
    private final int xBlockDim;
    private SudokuBlock[][] blocks;
    private int xBlocks;
    private int yBlocks;

    public Sudoku() {
        this.xBlocks = 3;
        this.yBlocks = 3;

        this.xBlockDim = 3;
        this.yBlockDim = 3;

        this.blocks = new SudokuBlock[this.yBlockDim][this.xBlocks];
        for(int y = 0; y < yBlocks; y++) {
            for (int x = 0; x < xBlocks; x++) {
                this.blocks[y][x] = new SudokuBlock(x,y,this.xBlockDim,this.yBlockDim);
            }
        }
    }

    public void addField(int x, int y, int value) {
        int xBlockCoord = x / this.xBlocks;
        int yBlockCoord = y / this.xBlocks;
        this.blocks[yBlockCoord][xBlockCoord].addField(x % this.xBlockDim,y % this.yBlockDim, new SudokuField(this.blocks[yBlockCoord][xBlockCoord],x % this.xBlockDim,y % this.yBlockDim, value));
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < this.yBlockDim*this.yBlocks;i++) {
            b.append(Arrays.stream(getRow(i))
                    .map(f -> f.getValue() != null ? String.valueOf(f.getValue()) : "["+f.getPossibleValues().stream().map(k -> String.valueOf(k)).collect(Collectors.joining(","))+"]")
                    .collect(Collectors.joining(";")) + System.getProperty("line.separator"));
        }
        return b.toString();
    }



    private SudokuField[] getRow(int rowNum) {
        SudokuField[] out = new SudokuField[this.xBlockDim*this.xBlocks];
        int index = 0;
        int blockYCoord = rowNum / this.xBlockDim;
        for (int i = 0; i < this.xBlocks; i++) {
            SudokuBlock block = this.blocks[blockYCoord][i];
            for (int j = 0; j < block.getXDim();j++) {
                SudokuField field = block.getField(j,rowNum % this.xBlockDim);
                out[index++] = field;
            }
        }
        return out;
    }


    private SudokuField[] getColumn(int colNum) {
        SudokuField[] out = new SudokuField[this.yBlockDim*this.yBlocks];
        int index = 0;
        int blockYCoord = colNum / this.yBlockDim;
        for (int i = 0; i < this.yBlocks; i++) {
            SudokuBlock block = this.blocks[i][blockYCoord];
            for (int j = 0; j < block.getYDim();j++) {
                SudokuField field = block.getField(colNum % this.yBlockDim, j);
                out[index++] = field;
            }
        }
        return out;
    }

    public void solve() {
        calculateCandidates();
        while(this.isNotSolved()) {
            Iterator<SudokuField> possibleValuesIterator = getUnsolvedFieldIterator(sortFieldsByPossibleValues());
            while(possibleValuesIterator.hasNext()) {
                SudokuField f = possibleValuesIterator.next();
                System.out.println("processing "+ f.toString());
                SudokuField[] row = getRow(this.getRowIndex(f));
                SudokuField[] column = getColumn(this.getColIndex(f));
                if (f.getPossibleValues().size() == 1) {
                    f.setValue(f.getPossibleValues().get(0), row, column);
                    continue;
                } else {
                    if (trySolvingFields(Arrays.stream(f.getBlock().getFields()).flatMap(s -> Stream.of(s)).toArray(SudokuField[]::new)) == Boolean.TRUE
                     || trySolvingFields(row) == Boolean.TRUE
                     || trySolvingFields(column) == Boolean.TRUE) {
                        continue;
                    }
                }
            }
        }
    }

    private Iterator<SudokuField> getUnsolvedFieldIterator(List<SudokuField> listPossibleValues) {
        return listPossibleValues.stream().filter(s -> s.getValue() == null).iterator();
    }

    private boolean isNotSolved() {
        return Arrays.stream(this.blocks)
                .flatMap(s -> Arrays.stream(s))
                .map(b -> b.getFields())
                .flatMap(ba -> Arrays.stream(ba))
                .flatMap(ba -> Arrays.stream(ba))
                .filter(s -> s.getValue() == null).count() > 0l ? true : false;
    }


    private boolean trySolvingFields(SudokuField [] sudokuFieldArray) {

        Map<String, List<SudokuField>> samePossibleValuesMap = Arrays.stream(sudokuFieldArray)
                .filter(s -> s.getPossibleValues().size() > 0)
                .collect(Collectors.groupingBy(
                        s -> s.getPossibleValues()
                                .stream()
                                .map(i -> String.valueOf(i))
                                .collect(Collectors.joining(" ")
                                )));

        //filter those where the amount of possible numbers equals the number of fields
        Map<String, List<SudokuField>> possibleValuesEqualsFieldSizeMap = samePossibleValuesMap
                .entrySet()
                .stream()
                .filter(e -> e.getKey().split(" ").length == e.getValue().size())
                .collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue()));

        if (possibleValuesEqualsFieldSizeMap.entrySet().size() > 0) {
            Iterator<Map.Entry<String,List<SudokuField>>> it = possibleValuesEqualsFieldSizeMap.entrySet().iterator();

            while(it.hasNext()) {
                Map.Entry<String, List<SudokuField>> entry = it.next();
                //remove those entries from all other elements the given array
                List<SudokuField> otherSudokuFields = new LinkedList<>(Arrays.asList(sudokuFieldArray));
                otherSudokuFields.removeAll(entry.getValue());

                List<Integer> valuesToRemove = Arrays.asList(entry.getKey().split(" "))
                        .stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                otherSudokuFields.forEach(s -> s.getPossibleValues().removeAll(valuesToRemove));

                if (areFieldsInSameRow(entry.getValue())) {
                    List<SudokuField> row = new LinkedList<>(Arrays.asList(getRow(getRowIndex(entry.getValue().get(0)))));
                    row.removeAll(entry.getValue());
                    row.forEach(s -> s.getPossibleValues().removeAll(valuesToRemove));
                } else if (areFieldsInSameColumn(entry.getValue())) {
                    List<SudokuField> column = new LinkedList<>(Arrays.asList(getColumn(getColIndex(entry.getValue().get(0)))));
                    column.removeAll(entry.getValue());
                    column.forEach(s -> s.getPossibleValues().removeAll(valuesToRemove));
                }
            }
            return true;
        }

        //check if one possible value has a single occurence
        Integer singlePossibleValue = containsSinglePossibleValue(sudokuFieldArray);

        if (singlePossibleValue != null) {
            List<SudokuField> fields = getFieldsByValue(singlePossibleValue, sudokuFieldArray);

            if (fields.size() == 1 ) {
                SudokuField[] row = getRow(getRowIndex(fields.get(0)));
                SudokuField[] column = getColumn(getColIndex(fields.get(0)));
                System.out.println("solving "+fields.get(0).toString() +" with value "+singlePossibleValue);
                fields.get(0).setValue(singlePossibleValue,row,column);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean areFieldsInSameRow(List<SudokuField> sudokuFields) {
        return sudokuFields.stream().map(s -> getRowIndex(s)).distinct().count() == 1 ? true : false;
    }

    private boolean areFieldsInSameColumn(List<SudokuField> sudokuFields) {
        return sudokuFields.stream().map(s -> getColIndex(s)).distinct().count() == 1 ? true : false;
    }

    private List<SudokuField> getFieldsByValue(Integer singlePossibleValuesMap, SudokuField [] sudokuFieldArray) {
        return Arrays.stream(sudokuFieldArray).filter(s -> s.getPossibleValues().contains(singlePossibleValuesMap)).collect(Collectors.toList());
    }

    private Integer containsSinglePossibleValue(SudokuField [] sudokuFieldArray) {
        Map<Integer,List<SudokuField>> map = Arrays.stream(sudokuFieldArray).filter(s-> s.getPossibleValues().size() > 0)
                .collect(Collectors.groupingBy(s -> s.getPossibleValues().size()) );

        if (map.isEmpty()) {
            System.out.println("ERROR");
            return null;
        }

        Map<Integer, Long> mapPossibleValuesGroupedByOccurence = map.values()
                .stream()
                .flatMap(List::stream)
                .map(s -> s.getPossibleValues())
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        return mapPossibleValuesGroupedByOccurence
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1l)
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }

    private int getRowIndex(SudokuField f) {
        return f.getBlock().getY()*this.yBlockDim + f.getY();
    }

    private int getColIndex(SudokuField f) {
        return f.getBlock().getX()*this.xBlockDim + f.getX();
    }

    private List<SudokuField> sortFieldsByPossibleValues() {
        Stream<SudokuBlock> l = Arrays.stream(this.blocks).flatMap(b -> Stream.of(b));
        Stream<SudokuField[][]> j = l.map( b -> b.getFields());
        Stream<SudokuField> u = j
                .flatMap(f -> Arrays.stream(f))
                .flatMap(f -> Stream.of(f));

        return u.filter(s -> s.getPossibleValues().size() > 0)
                .sorted( (s1,s2) -> {
                    if (s1.getPossibleValues().size() == s2.getPossibleValues().size()) {
                        return 0;
                    }
                    if (s1.getPossibleValues().size() < s2.getPossibleValues().size()){
                        return -1;
                    }
                    return 1;
                } ).collect(Collectors.toList());
    }

    private void compareColsAndRows() {
        SudokuField[][] s1 = new SudokuField[9][9];
        SudokuField[][] s2 = new SudokuField[9][9];
        for (int i = 0; i  < 9;i++) {
            SudokuField[] row = getRow(i);
            SudokuField[] col = getColumn(i);
            s1[i] = row;
            for (int k =0; k < 9;k++) {
                s2[k][i] = col[k];
            }
        }

        boolean valid = true;
        for (int i = 0; i < 9;i++) {
            for (int j = 0; j < 9;j++) {
                valid = valid && s1[i][j].getValue() == s2[i][j].getValue();
            }
        }

        System.out.println(valid);

    }

    private void calculateCandidates() {
        for ( int i = 0; i < this.yBlocks;i++) {
            for (int j = 0 ; j < this.xBlocks;j++) {
                SudokuBlock b = this.blocks[i][j];
                for (int i1 = 0; i1 < b.getYDim();i1++) {
                    for (int j1 = 0; j1 < b.getXDim();j1++) {
                        SudokuField f = b.getField(j1,i1);
                        if (f.getValue() == null) {
                            SudokuField[] row = this.getRow(i*yBlockDim+i1);
                            SudokuField[] col = this.getColumn(j*xBlockDim+j1);
                            removeValues(f, row, col, b);
                        }
                    }
                }
            }
        }
    }

    private void removeValues(SudokuField f, SudokuField[] row, SudokuField[] col, SudokuBlock block) {
        Stream<SudokuField> blockStream = Arrays.stream(block.getFields()).flatMap(a -> Arrays.stream(a));
        Stream<SudokuField> rowStream = Arrays.stream(row);
        Stream<SudokuField> colStream = Arrays.stream(col);
        List<Integer> presentValues = Stream.concat(Stream.concat(blockStream, rowStream), colStream)
                .map(SudokuField::getValue)
                .filter(i -> i != null)
                .distinct()
                .sorted().collect(Collectors.toList());
        f.getPossibleValues().removeAll(presentValues);

    }

}
