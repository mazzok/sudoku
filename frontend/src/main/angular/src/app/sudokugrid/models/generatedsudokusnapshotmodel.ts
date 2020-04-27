import {GeneratedSudokuSnapshotBlockModel} from "./generatedsudokusnapshotblockmodel";

export interface GeneratedSudokuSnapshotModel {
    message: string;
    xDim: number;
    yDim: number;
    generatedSudokuBlocks: GeneratedSudokuSnapshotBlockModel[];
}
