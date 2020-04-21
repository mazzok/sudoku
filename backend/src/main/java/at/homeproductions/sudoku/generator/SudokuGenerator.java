package at.homeproductions.sudoku.generator;

import at.homeproductions.sudoku.entity.generator.GeneratedSudoku;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuField;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuSnapshot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuGenerator {

    public enum Direction {
        ROW(0),
        COLUMN(1);

        private int value;

        Direction(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }

    public static GeneratedSudoku generate() {
        GeneratedSudoku s = new GeneratedSudoku();

        List<GeneratedSudokuSnapshot> generatedSnapshots;

        System.out.println(s);

        for (int x = 0, y = 0; x < 9 && y < 9; x++,y++) {
            GeneratedSudokuField[] row = s.getRow(y);
            GeneratedSudokuField[] column = s.getColumn(x);

            List<GeneratedSudokuField[]> data = Arrays.asList(row, column);
            System.out.println("Processing Row "+y);
            exchangeInBlock(s, data, Direction.ROW,y);
            GeneratedSudokuSnapshot generatedSudokuSnapshot = new GeneratedSudokuSnapshot("Processing Row "+y, s);

            System.out.println("Processing Column "+x);
            exchangeInBlock(s, data, Direction.COLUMN,x);
        }

        if (!s.isValid()) {
            throw new IllegalStateException("The generated sudoku is not valid:"+s.toString());
        }
        return s;
    }

    private static void exchangeInBlock(GeneratedSudoku sudoku, List<GeneratedSudokuField[]> colRowData, Direction direction, int rowOrColumnNum) {
        Map<Integer, GeneratedSudokuField> registeredMap = new LinkedHashMap<>();

        List<Integer> dataValues = Arrays.stream(colRowData.get(direction.getValue()))
                .map(GeneratedSudokuField::getValue).collect(Collectors.toList());

        GeneratedSudokuField[] data = colRowData.get(direction.getValue());
        for (int i = 0; i < data.length;i++) {
            GeneratedSudokuField f = data[i];
            f.setSorted(true);
            if (registeredMap.get(f.getValue()) == null) {
                registeredMap.put(f.getValue(), f);
                continue;
            } else {
                //duplicate case!
                System.out.println(f.getValue()+" is duplicate!");
                if (f.isSorted()) {
                    System.out.println(" and it is already sorted! apply backtracing");
                    backtraceAdjacentValues(f, data,i, registeredMap, direction, rowOrColumnNum);
                } else {
                    List<GeneratedSudokuField> freeFields = Arrays.stream(f.getBlock().getFields())
                            .flatMap(Arrays::stream)
                            .filter(s -> sudoku.isNotInColOrRow(s, rowOrColumnNum))
                            .filter(s -> !s.isSorted())
                            .filter(s -> !dataValues.contains(s.getValue()))
                            .filter(s -> registeredMap.get(s.getValue()) == null)
                            .collect(Collectors.toList());

                    if (freeFields.size() > 0) {
                        GeneratedSudokuField exchangeValueField = freeFields.get(0);
                        System.out.println(String.format("Exchanging duplicate %s with free field value %s in its block", f.getValue(), exchangeValueField.getValue()));

                        exchangeValue(f, exchangeValueField);
                        updateRegisteredMap(i, registeredMap,data);
                        System.out.println("############");
                        System.out.println(sudoku.toString());
                        System.out.println("############");
                        continue;
                    } else {
                        System.out.println("No free fields for duplicate value "+f.getValue()+" trying adjacent elements!");
                        backtraceAdjacentValues(f, data,i, registeredMap, direction, rowOrColumnNum);
                    }
                }
            }
        }
    }


    private static void updateRegisteredMap(Integer upToIncludeIndex, Map<Integer, GeneratedSudokuField> registeredMap, GeneratedSudokuField[] data) {
        registeredMap.clear();
        for(int i =0 ; i<= upToIncludeIndex;i++) {
            registeredMap.put(data[i].getValue(), data[i]);
        }
    }

    private static void exchangeValue(GeneratedSudokuField to, GeneratedSudokuField from) {
        Integer fieldValue = to.getValue();
        to.setValue(from.getValue());
        from.setValue(fieldValue);
        System.out.println(to.getBlock().getSudoku().toString());
    }

    private static void backtraceAdjacentValues(GeneratedSudokuField f, GeneratedSudokuField[] data, Integer index, Map<Integer, GeneratedSudokuField> registeredMap, Direction direction, int rowOrColumnNum) {
        System.out.println(String.format("backtrace possible value %s for current %s %s...",f.getValue(), direction, rowOrColumnNum));
        Optional<GeneratedSudokuField> sameValueField = Arrays.stream(data)
                .filter(field -> field.getValue().equals(f.getValue()))
                .filter(field -> !field.equals(f))
                .filter(field -> field.isSorted())
                .findFirst();
        if (sameValueField.isPresent()) {
            System.out.println(String.format("The first possible field with value %s is %s",f.getValue(), sameValueField.get().toString()));
            List<GeneratedSudokuField> adjancentValues = getAdjacentValues(sameValueField.get(), direction);
            System.out.println("The adjacent values of the first occurence of "+sameValueField.get().getValue()+" are "+adjancentValues.stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList()));
            if (!adjancentValues.isEmpty()) {
                //see if any of the adjacent numbers is still not registered
                List<Integer> registeredMapValues = registeredMap.values().stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList());
                Optional<GeneratedSudokuField> freeValue = adjancentValues.stream().filter(g -> !registeredMapValues.contains(g.getValue())).findFirst();
                if (freeValue.isPresent()) {
                    System.out.println(freeValue.get().getValue()+" is not yet chosen - take that");
                    exchangeValue(sameValueField.get(), freeValue.get());
                    updateRegisteredMap(index, registeredMap,data);
                } else {
                    System.out.println("The first adjacent value "+adjancentValues.get(0).getValue()+" is already registered, switch that with first occurence of "+sameValueField.get().getValue()+" and iteratively backtrace");
                    exchangeValue(sameValueField.get(), adjancentValues.get(0));
                    updateRegisteredMap(index, registeredMap,data);
                    backtraceAdjacentValues(sameValueField.get(), data, index, registeredMap, direction, rowOrColumnNum);
                }
            }
        }
    }

    private static List<GeneratedSudokuField> getAdjacentValues(GeneratedSudokuField f, Direction direction) {

        Stream<GeneratedSudokuField> stream = Arrays.stream(f.getBlock().getFields())
                .flatMap(Arrays::stream);

        if (Direction.ROW == direction) {
            stream = stream.filter(s -> s.getX() == f.getX())
                    .filter(s -> s.getY() > f.getY());
        } else if (Direction.COLUMN == direction) {
            stream = stream.filter(s -> s.getY() == f.getY())
                    .filter(s -> s.getX() > f.getX());
        }

        return stream.collect(Collectors.toList());
    }

    private static boolean isExchangeInBlockPossible(GeneratedSudokuField [] data, GeneratedSudokuField duplicateField, List<GeneratedSudokuField> listOfFieldsToExclude) {
        List<GeneratedSudokuField> freeFields = Arrays.stream(duplicateField.getBlock().getFields())
                .flatMap(Arrays::stream)
                .filter(s -> !listOfFieldsToExclude.contains(s))
                .collect(Collectors.toList());

        boolean exchangeInBlockPossible = false;
        for (GeneratedSudokuField freeField : freeFields) {
            List<Integer> dataListValues = Arrays.asList(data).stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList());
            if (!dataListValues.contains(freeField.getValue())) {
                System.out.println(String.format("Exchanging duplicate %s with free field value %s in its block", duplicateField.getValue(), freeField.getValue()));
                duplicateField.setValue(freeField.getValue());
                exchangeInBlockPossible = true;
            }
        }
        return exchangeInBlockPossible;
    }

}
