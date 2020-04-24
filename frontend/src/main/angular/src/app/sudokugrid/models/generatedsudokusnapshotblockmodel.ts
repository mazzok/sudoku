import { SudokuSnapshotFieldModel } from "./sudokusnapshotfieldmodel";
import {GeneratedSudokuSnapshotFieldModel} from "./generatedsudokusnapshotfieldmodel";

export interface GeneratedSudokuSnapshotBlockModel {
    xDim: number;
    yDim: number;
    x: number;
    y: number;
    generatedSudokuFields: GeneratedSudokuSnapshotFieldModel[];
}
