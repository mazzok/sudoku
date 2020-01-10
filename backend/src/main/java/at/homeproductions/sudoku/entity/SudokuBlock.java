package at.homeproductions.sudoku.entity;

public class SudokuBlock {


    protected SudokuField[][] fields;
    private Sudoku sudoku;
    protected int x;
    protected int y;
    protected int xDim;
    protected int yDim;

    protected SudokuBlock() {
        super();
    }

    public SudokuBlock(Sudoku sudoku, int x, int y, int xDim, int yDim) {
        this.sudoku = sudoku;
        this.x = x;
        this.y = y;
        this.xDim = xDim;
        this.yDim = yDim;

        this.fields = createFieldArray(xDim, yDim);
        for(int fieldY = 0; fieldY < yDim; fieldY++) {
            for (int fieldX = 0; fieldX < xDim; fieldX++) {
                this.fields[fieldY][fieldX] = createSudokuField(fieldY, fieldX);
            }
        }
    }

    protected SudokuField createSudokuField(int fieldY, int fieldX) {
        return new SudokuField(this,fieldX,fieldY);
    }

    protected SudokuField[][] createFieldArray(int xDim, int yDim) {
        return new SudokuField[xDim][yDim];
    }

    public void addField(int x, int y, SudokuField sudokuField) {
        this.fields[y][x] = sudokuField;
    }

    public int getYDim() {
        return this.yDim;
    }

    public SudokuField[][] getFields() {
        return this.fields;
    }

    public int getXDim() {
        return  this.xDim;
    }


    public SudokuField getField(int x, int y) {
        return this.fields[y][x];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }
}
