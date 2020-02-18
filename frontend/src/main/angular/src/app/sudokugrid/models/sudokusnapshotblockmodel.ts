import { SudokuSnapshotFieldModel } from "./sudokusnapshotfieldmodel";

export interface SudokuSnapshotBlockModel {
  xDim: number;
  yDim: number;
  x: number;
  y: number;
  sudokuFields: SudokuSnapshotFieldModel[];
}
