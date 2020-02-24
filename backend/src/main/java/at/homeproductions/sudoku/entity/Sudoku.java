package at.homeproductions.sudoku.entity;

import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshot;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotField;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sudoku extends AbstractSudoku<SudokuField, SudokuBlock, Sudoku>{

    private List<SudokuSnapshot> snapshots;

    public Sudoku() {
        super();
        this.snapshots = new ArrayList<>();
    }

    public static Sudoku getDefaulSudoku() {
       Sudoku s = new Sudoku();
        s.addInitialField(1, 0, 3);
        s.addInitialField(3, 1, 1);
        s.addInitialField(4, 1, 9);
        s.addInitialField(5, 1, 5);
        s.addInitialField(2, 2, 8);
        s.addInitialField(7, 2, 6);
        s.addInitialField(0, 3, 8);
        s.addInitialField(4, 3, 6);
        s.addInitialField(0, 4, 4);
        s.addInitialField(3, 4, 8);
        s.addInitialField(8, 4, 1);
        s.addInitialField(4, 5, 2);
        s.addInitialField(1, 6, 6);
        s.addInitialField(6, 6, 2);
        s.addInitialField(7, 6, 8);
        s.addInitialField(3, 7, 4);
        s.addInitialField(4, 7, 1);
        s.addInitialField(5, 7, 9);
        s.addInitialField(8, 7, 5);
        s.addInitialField(7, 8, 7);
        return s;
    }

    @Override
    protected SudokuBlock createSudokuBlock(int y, int x) {
        return new SudokuBlock(this,x,y,this.xBlockDim,this.yBlockDim);
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < this.yBlockDim*this.yBlocks;i++) {
            b.append(Arrays.stream(getRow(i))
                    .map(f -> f.getValue() != null ? String.valueOf(f.getValue()) : "["+f.getPossibleValues().stream().map(k -> String.valueOf(k.getValue()) + (k.getIsHidden() == true ? "(h)" : "")).collect(Collectors.joining(","))+"]")
                    .collect(Collectors.joining(";")) + System.getProperty("line.separator"));
        }
        return b.toString();
    }


    public void addInitialField(int x, int y, int value) {
        int xBlockCoord = x / this.xBlocks;
        int yBlockCoord = y / this.xBlocks;
        SudokuField field = new SudokuField(this.blocks[yBlockCoord][xBlockCoord],x % this.xBlockDim,y % this.yBlockDim, value);
        field.setIsInitialField(true);
        this.blocks[yBlockCoord][xBlockCoord].addField(x % this.xBlockDim,y % this.yBlockDim, field);
        calculateCandidates();
    }


    @Override
    protected Class<SudokuField> getFieldClass() {
        return SudokuField.class;
    }

    @Override
    protected Class<SudokuBlock> getBlockClass() {
        return SudokuBlock.class;
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
                if (f.getPossibleValues().stream().filter(p->!p.getIsHidden()).count() == 1) {
                    f.setValue(f.getPossibleValues().stream().filter(p->!p.getIsHidden()).findFirst().get().getValue(),true);
                    continue;
                } else {
                    if (trySolvingFields(Arrays.stream(f.getBlock().getFields()).flatMap(s -> Stream.of(s)).toArray(SudokuField[]::new),"block") == Boolean.TRUE
                            || trySolvingFields(row,"row") == Boolean.TRUE
                            || trySolvingFields(column,"column") == Boolean.TRUE) {
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


    private boolean trySolvingFields(SudokuField[] sudokuFieldArray, String type) {

        Map<String, List<SudokuField>> samePossibleValuesMap = Arrays.stream(sudokuFieldArray)
                .filter(s -> s.getPossibleValues().stream().anyMatch(p -> !p.getIsHidden()))
                .collect(Collectors.groupingBy(
                        s -> s.getPossibleValues()
                                .stream()
                                .filter(p -> !p.getIsHidden())
                                .map(i -> String.valueOf(i.getValue()))
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

                if (entry.getValue().stream().allMatch(p -> p.getIsValueReserved() == true)) {
                    continue;
                }

                if (entry.getValue().size() == 1 && entry.getValue().get(0).getPossibleValues().stream().filter(p -> p.getIsHidden() == false).count() == 1l) {
                    continue;
                }

                //remove those entries from all other elements the given array
                List<SudokuField> otherSudokuFields = new LinkedList<>(Arrays.asList(sudokuFieldArray));
                otherSudokuFields.removeAll(entry.getValue());

                List<Integer> valuesToHide = Arrays.asList(entry.getKey().split(" "))
                        .stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                otherSudokuFields.forEach(s -> s.getPossibleValues()
                        .stream()
                        .filter(p -> valuesToHide.contains(p.getValue()))
                        .forEach(p-> p.setIsHidden(true)));
                //hide all entries possible values
                entry.getValue().forEach(f -> f.setIsValueReserved(true));

                String message = String.format("There are %s field within the same %s, which can only contain one of the values %s." +
                                " Therefore these values are removed from all other fields of the same %s",
                        entry.getValue().size(),
                        type,
                        Arrays.asList(entry.getKey().split(",")),
                        type);
                this.logSolutionTrailStep(message, entry.getValue(), otherSudokuFields);



                if (type == "block") {
                    if (areFieldsInSameRow(entry.getValue()) && areAnyFieldsNotSet(entry.getValue())) {
                        List<SudokuField> row = new LinkedList<>(Arrays.asList(getRow(getRowIndex(entry.getValue().get(0)))));
                        row.removeAll(entry.getValue());
                        row.forEach(s -> s.getPossibleValues()
                                .stream()
                                .filter(p -> valuesToHide.contains(p.getValue()))
                                .forEach(p-> p.setIsHidden(true)));

                        this.logSolutionTrailStep(String.format("These fields are additionally in the same row, therefore values [%s] are removed from there aswell"
                                ,Arrays.asList(entry.getKey().split(","))), entry.getValue(), row);

                    } else if (areFieldsInSameColumn(entry.getValue())) {
                        List<SudokuField> column = new LinkedList<>(Arrays.asList(getColumn(getColIndex(entry.getValue().get(0)))));
                        column.removeAll(entry.getValue());
                        column.forEach(s -> s.getPossibleValues()
                                .stream()
                                .filter(p -> valuesToHide.contains(p.getValue()))
                                .forEach(p-> p.setIsHidden(true)));

                        this.logSolutionTrailStep(String.format("These fields are additionally in the same column, therefore values [%s] are removed from there aswell"
                                ,Arrays.asList(entry.getKey().split(","))), entry.getValue(), column);

                    }
                }
            }
            return true;
        }

        //check if one possible value has a single occurence
        Integer singlePossibleValue = containsSinglePossibleValue(sudokuFieldArray);

        if (singlePossibleValue != null) {
            List<SudokuField> fields = getFieldsByValue(singlePossibleValue, sudokuFieldArray);

            if (fields.size() == 1 ) {
                fields.get(0).setValue(singlePossibleValue,true);

                SudokuSnapshot currentTrail = this.snapshots.get(this.snapshots.size()-1);
                if (currentTrail != null) {
                    String message = String.format("%s is the single possible value for field %s in its %s",
                            singlePossibleValue,
                            fields.get(0),
                            type
                    );
                    currentTrail.setMessage(message + "."+ currentTrail.getMessage());
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean areAnyFieldsNotSet(List<SudokuField> sudokuFields) {
        return sudokuFields.stream().anyMatch(s -> s.getValue() == null);
    }

    private boolean areFieldsInSameRow(List<SudokuField> sudokuFields) {
        return sudokuFields.stream().map(s -> getRowIndex(s)).distinct().count() == 1 ? true : false;
    }

    private boolean areFieldsInSameColumn(List<SudokuField> sudokuFields) {
        return sudokuFields.stream().map(s -> getColIndex(s)).distinct().count() == 1 ? true : false;
    }

    private List<SudokuField> getFieldsByValue(Integer singlePossibleValuesMap, SudokuField[] sudokuFieldArray) {
        return Arrays.stream(sudokuFieldArray)
                .filter(s -> s.getPossibleValues()
                        .stream()
                        .filter(p -> !p.getIsHidden() && p.getValue() == singlePossibleValuesMap)
                        .count() > 0)
                .collect(Collectors.toList());
    }

    protected Integer containsSinglePossibleValue(SudokuField[] sudokuFieldArray) {
        Map<Integer,List<SudokuField>> map = Arrays.stream(sudokuFieldArray)
                .filter(s-> s.getPossibleValues().stream().filter(p-> !p.getIsHidden()).count() > 0)
                .collect(Collectors.groupingBy(s -> (int)s.getPossibleValues().stream().filter(p-> !p.getIsHidden()).count() ) );

        if (map.isEmpty()) {
            System.out.println("ERROR, Map is empty!");
            return null;
        }

        Map<Integer, Long> mapPossibleValuesGroupedByOccurence = map.values()
                .stream()
                .flatMap(List::stream)
                .map(s -> s.getPossibleValues())
                .flatMap(List::stream)
                .filter(p -> !p.getIsHidden())
                .map(PossibleValue::getValue)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        return mapPossibleValuesGroupedByOccurence
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1l)
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }

    private List<SudokuField> sortFieldsByPossibleValues() {
        Stream<SudokuBlock> l = Arrays.stream(this.blocks).flatMap(b -> Stream.of(b));
        Stream<SudokuField[][]> j = l.map(b -> b.getFields());
        Stream<SudokuField> u = j
                .flatMap(f -> Arrays.stream(f))
                .flatMap(f -> Stream.of(f));

        return u.filter(s -> s.getPossibleValues()
                .stream()
                .anyMatch(p->!p.getIsHidden()))
                .sorted( (s1,s2) -> {
                    long s1NotHiddenValues = s1.getPossibleValues().stream().filter(p->!p.getIsHidden()).count();
                    long s2NotHiddenValues = s2.getPossibleValues().stream().filter(p->!p.getIsHidden()).count();
                    if (s1NotHiddenValues == s2NotHiddenValues) {
                        return 0;
                    }
                    if (s1NotHiddenValues < s2NotHiddenValues){
                        return -1;
                    }
                    return 1;
                } ).collect(Collectors.toList());
    }

    public void calculateCandidates() {
        for ( int i = 0; i < this.yBlocks;i++) {
            for (int j = 0 ; j < this.xBlocks;j++) {
                SudokuBlock b = this.blocks[i][j];
                for (int i1 = 0; i1 < b.getYDim();i1++) {
                    for (int j1 = 0; j1 < b.getXDim();j1++) {
                        SudokuField f = b.getField(j1,i1);
                        SudokuField[] row = this.getRow(i*yBlockDim+i1);
                        SudokuField[] col = this.getColumn(j*xBlockDim+j1);
                        removeValues(f, row, col, b);
                    }
                }
            }
        }
    }

    private void removeValues(SudokuField f, SudokuField[] row, SudokuField[] col, SudokuBlock block) {
        List<Integer> presentValues = FieldVincinityCalculator.getValues(f, row, col);
        f.getPossibleValues()
                .removeIf(p -> presentValues.contains(p.getValue()));
    }

    public void logSolutionTrailStep(String message, List<SudokuField> actors, List<SudokuField> reactors) {
        SudokuSnapshot snapshot = new SudokuSnapshot(message,actors, reactors, this);

        this.snapshots.add(snapshot);
    }

    public void printSolutionTrail() {
        for (int i = 0; i < this.snapshots.size();i++) {
            SudokuSnapshot s = this.snapshots.get(i);
            System.out.println(String.format("----------(STEP %s.)-------------",i));

            System.out.println(s.getMessage());
            System.out.println(s.toString());

        }
    }

    public List<SudokuSnapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<SudokuSnapshot> snapshots) {
        this.snapshots = snapshots;
    }
}
