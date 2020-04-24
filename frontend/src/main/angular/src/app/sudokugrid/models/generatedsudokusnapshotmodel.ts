import {GeneratedSudokuSnapshotBlockModel} from "./generatedsudokusnapshotblockmodel";

export interface GeneratedSudokusnapshotModel {
    message: string;
    xDim: number;
    yDim: number;
    generatedSudokuBlocks: GeneratedSudokuSnapshotBlockModel[];
}
