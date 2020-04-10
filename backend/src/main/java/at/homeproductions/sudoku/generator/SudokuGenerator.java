package at.homeproductions.sudoku.generator;

import at.homeproductions.sudoku.entity.SudokuField;
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
            exchangeInBlock(s, row, Direction.ROW);
            System.out.println(s);

            System.out.println("Exchange InBlock for COLUMN "+x);
            exchangeInBlock(s, column, Direction.COLUMN);
            System.out.println(s);

//              System.out.println(rowDuplicates.stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList()));
//            System.out.println(columnDuplicates.stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList()));
        }
        return s;
    }

    private static void markSorted(GeneratedSudokuField[] row) {
        Arrays.stream(row).forEach(f -> f.setSorted(true));
    }

    private static void exchangeInBlock(GeneratedSudoku sudoku, GeneratedSudokuField[] data, Direction direction) {
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

                    backtraceAdjacentValues(f, data,i, registeredMap, direction);
//                    List<GeneratedSudokuField> freeAdjancentValues = getAdjacentValues(f, direction)
//                            .stream()
//                            .filter(s -> !dataValues.contains(s.getValue()))
//                            .filter(s -> registeredMap.get(s.getValue()) == null)
//                            .collect(Collectors.toList());
//
//                    if (!freeAdjancentValues.isEmpty()) {
//                        Integer fieldValue = f.getValue();
//                        GeneratedSudokuField exchangeValueField = freeAdjancentValues.get(0);
//                        System.out.println(String.format("Exchanging duplicate %s with free field value %s in its block", f.getValue(), exchangeValueField.getValue()));
//                        f.setValue(exchangeValueField.getValue());
//                        exchangeValueField.setValue(fieldValue);
//                        registeredMap.put(f.getValue(), f);
//                    }
                }

            }

            System.out.println(sudoku);
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

    private static void backtraceAdjacentValues(GeneratedSudokuField f, GeneratedSudokuField[] data, Integer index, Map<Integer, GeneratedSudokuField> registeredMap, Direction direction) {
        System.out.println(String.format("backtrace possible values from first occurences for %s ...", direction));
        GeneratedSudokuField firstOccurence = registeredMap.get(f.getValue());
        List<Integer> registeredMapValues = registeredMap.values().stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList());
        if (firstOccurence == null) {
            System.out.println(f.getValue()+" is the correct fill!");
            return;
        }
        System.out.println(String.format("The first possible first occurences of value %s is %s",f.getValue(),firstOccurence.toString()));
        List<GeneratedSudokuField> adjancentValues = getAdjacentValues(firstOccurence, direction);
        System.out.println("The adjacent values of the first occurence of "+firstOccurence.getValue()+" are "+adjancentValues.stream().map(GeneratedSudokuField::getValue).collect(Collectors.toList()));
        if (!adjancentValues.isEmpty()) {
            //see if any of the ddjacent numbers is still not regstered
            Optional<GeneratedSudokuField> freeValue = adjancentValues.stream().filter(g -> !registeredMapValues.contains(g.getValue())).findFirst();
            if (freeValue.isPresent()) {
                System.out.println(freeValue.get().getValue()+" is not yet chosen - take that");
                exchangeValue(firstOccurence, freeValue.get());
                updateRegisteredMap(index, registeredMap,data);

            } else {
                System.out.println("The first adjacent value "+adjancentValues.get(0).getValue()+" is already registered, switch that with first occurence of "+firstOccurence.getValue()+" and iteratively backtrace");
                exchangeValue(firstOccurence, adjancentValues.get(0));
                updateRegisteredMap(index, registeredMap,data);
                backtraceAdjacentValues(firstOccurence, data, index, registeredMap, direction);
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
