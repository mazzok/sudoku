package at.homeproductions.sudoku.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldVincinityCalculator {
    public static List<SudokuField> getPossibleFieldVincinityReactors(SudokuField field) {
        return getFieldVincinityReactors(field)
                .stream()
//                .filter(s -> s.getValue() == null)
                .collect(Collectors.toList());
    }

    public  static List<SudokuField> getFieldVincinityReactors(SudokuField field) {
        SudokuField[] row = field.getBlock().getSudoku().getRow(field.getBlock().getSudoku().getRowIndex(field));
        SudokuField[] column = field.getBlock().getSudoku().getColumn(field.getBlock().getSudoku().getColIndex(field));

        return Stream.concat( Stream.of(field.getBlock().getFields()).flatMap(s -> Stream.of(s)) ,
                Stream.concat(Arrays.stream(row),Arrays.stream(column)))
                .distinct()
                .filter(s-> !s.equals(field))
                .collect(Collectors.toList());
    }

    public static List<SudokuField> getFieldsWithSameValue(SudokuField field) {
        List<SudokuField> l = getFieldVincinityReactors(field);
        return l.stream().filter(s -> field.getValue().equals(s.getValue())).collect(Collectors.toList());
    }

    public static List<Integer> getValues(SudokuField f, SudokuField[] row, SudokuField[] col) {
        Stream<SudokuField> blockStream = Arrays.stream(f.getBlock().getFields()).flatMap(a -> Arrays.stream(a));
        Stream<SudokuField> rowStream = Arrays.stream(row);
        Stream<SudokuField> colStream = Arrays.stream(col);
        return Stream.concat(Stream.concat(blockStream, rowStream), colStream)
                .map(SudokuField::getValue)
                .filter(i -> i != null)
                .distinct()
                .sorted().collect(Collectors.toList());
    }

    public static void hideOrUnhidePossibleValues(Sudoku sudoku, SudokuField f) {
        SudokuField[] row = sudoku.getRow(sudoku.getRowIndex(f));
        SudokuField[] column = sudoku.getColumn(sudoku.getColIndex(f));
        Stream<SudokuField> blockStream = Arrays.stream(f.getBlock().getFields()).flatMap(a -> Arrays.stream(a));
        Stream<SudokuField> rowStream = Arrays.stream(row);
        Stream<SudokuField> colStream = Arrays.stream(column);


//        Stream<SudokuField> concatStream = Stream.concat(blockStream, Stream.concat(rowStream, colStream)).filter(s -> !s.equals(f));
//        hideOrUnhidePossibleValues(sudoku, concatStream);

        Stream<SudokuField> blockStream1 = blockStream.filter(s -> !s.equals(f));
        hideOrUnhidePossibleValues(sudoku, blockStream1);

        Stream<SudokuField> rowStream1 = rowStream.filter(s -> !s.equals(f));
        hideOrUnhidePossibleValues(sudoku, rowStream1);

        Stream<SudokuField> colStream1 = colStream.filter(s -> !s.equals(f));
        hideOrUnhidePossibleValues(sudoku, colStream1);


        //hide possible values which have been picked
    }

    public static void hideOrUnhidePossibleValues(Sudoku sudoku, Stream<SudokuField> sudokuFieldStream) {
        sudokuFieldStream.forEach( field -> {
                    SudokuField[] r = sudoku.getRow(sudoku.getRowIndex(field));
                    SudokuField[] c = sudoku.getColumn(sudoku.getColIndex(field));
                    List<Integer> presentValues = getValues(field, r, c);

                    for (PossibleValue pv : field.getPossibleValues()) {
                        if (presentValues.contains(pv.getValue())) {
                            pv.setIsHidden(true);
                        } else {
                            pv.setIsHidden(false);
                        }
                    }
                }
        );
    }
}

