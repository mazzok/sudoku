import { SudokuFieldModel } from "./sudokufieldmodel";

export interface SudokuBlockModel {
  xDim: number;
  yDim: number;
  x: number;
  y: number;
  sudokuFields: SudokuFieldModel[];
}
