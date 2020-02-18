import { SudokuModel } from "./sudokumodel";
import { SudokuSnapshotBlockModel } from "./sudokusnapshotblockmodel";

export interface SudokusnapshotModel {
  message: String;
  xDim: number;
  yDim: number;
  sudokuBlocks: SudokuSnapshotBlockModel[];
}
