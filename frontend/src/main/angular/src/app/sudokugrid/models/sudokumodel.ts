import { SudokuBlockModel } from "./sudokublockmodel";

export interface SudokuModel {
  xDim: number;
  yDim: number;
  sudokuBlocks: SudokuBlockModel[];
}
