package at.homeproductions.sudoku.generator;

import at.homeproductions.sudoku.entity.generator.GeneratedSudoku;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuField;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuGenerator {

    public enum Direction {
        ROW,
        COLUMN
    }

    public static GeneratedSudoku generate() {
        GeneratedSudoku s = new GeneratedSudoku();
        System.out.println(s);

        for (int x = 0, y = 0; x < 9 && y < 9; x++,y++) {
            GeneratedSudokuField[] row = s.getRow(y);
            GeneratedSudokuField[] column = s.getColumn(x);

            markSorted(row);
            markSorted(column);

            System.out.println("Exchange InBlock for ROW "+y);
            exchangeInBlock(s, row, Direction.ROW,y);
            System.out.println(s);

            System.out.println("Exchange InBlock for COLUMN "+x);
            exchangeInBlock(s, column, Direction.COLUMN,x);
            System.out.println(s);

            if (x > 1) {
                if(!s.checkAllColumnSumUpTo(x) || !s.checkAllColumnUpToNoDuplicates(x)) {
                    throw new IllegalStateException("The generated sudoku is not valid:"+s.toString());
                }
            }

            if (y > 1) {
                if (!s.checkAllRowSumUpTo(y) || !s.checkAllRowUpToNoDuplicates(y)){
                    throw new IllegalStateException("The generated sudoku is not valid:"+s.toString());
                }
            }

        }

        if (!s.isValid()) {
            throw new IllegalStateException("The generated sudoku is not valid:"+s.toString());
        }
        return s;
    }

    private static void markSorted(GeneratedSudokuField[] row) {
        Arrays.stream(row).forEach(f -> f.setSorted(true));
    }

    private static void exchangeInBlock(GeneratedSudoku sudoku, GeneratedSudokuField[] data, Direction direction, int rowOrColumnNum) {
        Map<Integer, GeneratedSudokuField> registeredMap = new LinkedHashMap<>();
        List<Integer> dataValues = Arrays.stream(data).map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        for (int i = 0; i < data.length;i++) {
            GeneratedSudokuField f = data[i];

            if (registeredMap.get(f.getValue()) == null) {
                registeredMap.put(f.getValue(), f);
                continue;
            } else {
                //duplicate case!
                System.out.println(f.getValue()+" is duplicate!");
                List<GeneratedSudokuField> freeFields = Arrays.stream(f.getBlock().getFields())
                        .flatMap(Arrays::stream)
                        .filter(s -> !s.isSorted())
                        .filter(s -> !dataValues.contains(s.getValue()))
                        .filter(s -> registeredMap.get(s.getValue()) == null)
                        .collect(Collectors.toList());
                if (!freeFields.isEmpty()) {
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
//            System.out.println(sudoku);
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

//    private static Map<Integer, List<GeneratedSudokuField>> findDuplicates(GeneratedSudokuField[] array) {
//        return Arrays.stream(array)
//                .collect(Collectors.groupingBy(GeneratedSudokuField::getValue))
//                .entrySet().stream()
//                .filter(e -> e.getValue().size() > 1)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
}
