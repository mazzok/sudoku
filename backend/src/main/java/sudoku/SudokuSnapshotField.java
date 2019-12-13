package sudoku;

public class SudokuSnapshotField extends SudokuField {
    private boolean isReactor;
    private boolean isActor;

    public SudokuSnapshotField(SudokuBlock block, int x, int y, Integer value) {
        super(block, x, y, value);
    }

    public SudokuSnapshotField(SudokuSnapshotBlock sudokuTrailBlock, int fieldY, int fieldX) {
        super(sudokuTrailBlock,fieldY,fieldX);
    }

    public SudokuSnapshotField(SudokuField sudokuField) {
        copy(sudokuField);
    }

    public static SudokuSnapshotField asActor(SudokuField s) {
        SudokuSnapshotField snapshotField = new SudokuSnapshotField(s);
        snapshotField.setActor(true);
        snapshotField.setReactor(false);
        return snapshotField;
    }


    public static SudokuSnapshotField asReactor(SudokuField s) {
        SudokuSnapshotField snapshotField = new SudokuSnapshotField(s);
        snapshotField.setActor(false);
        snapshotField.setReactor(true);
        return snapshotField;
    }

    private void copy(SudokuField sudokuField) {
        this.value = sudokuField.value;
        this.possibleValues= sudokuField.possibleValues;
    }


    public void setActor(boolean b) {
        this.isActor = b;
    }

    public void setReactor(boolean b) {
        this.isReactor = b;
    }

    public boolean isReactor() {
        return isReactor;
    }

    public boolean isActor() {
        return isActor;
    }
}
