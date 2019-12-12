package sudoku;

public class SudokuBlock {


    private SudokuField [][] fields;
    private final int x;
    private final int y;
    private int xDim;
    private int yDim;

    public SudokuBlock(int x, int y,int xDim, int yDim) {
        this.x = x;
        this.y = y;
        this.xDim = xDim;
        this.yDim = yDim;

        this.fields = new SudokuField[xDim][yDim];
        for(int fieldY = 0; fieldY < yDim; fieldY++) {
            for (int fieldX = 0; fieldX < xDim; fieldX++) {
                this.fields[fieldY][fieldX] = new SudokuField(this,fieldX,fieldY);
            }
        }
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
}
