import { SudokuBlockModel } from "./sudokublockmodel";
import { SudokusnapshotModel } from "./sudokusnapshotmodel";

export interface SudokuModel {
  xDim: number;
  yDim: number;
  sudokuBlocks: SudokuBlockModel[];
  snapshots: SudokusnapshotModel[];
}
