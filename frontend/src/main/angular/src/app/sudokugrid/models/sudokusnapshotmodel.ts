import { SudokuModel } from "./sudokumodel";
import { SudokuSnapshotBlockModel } from "./sudokusnapshotblockmodel";

export interface SudokusnapshotModel {
  message: string;
  xDim: number;
  yDim: number;
  sudokuBlocks: SudokuSnapshotBlockModel[];
}
