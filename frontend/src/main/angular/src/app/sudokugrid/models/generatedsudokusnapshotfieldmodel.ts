export interface GeneratedSudokuSnapshotFieldModel {
  x: number;
  y: number;
  value: number;
    isInColumnSelection : boolean;
    isInRowSelection : boolean;
    isInBlockSelection : boolean;
    isCurrentlyInSelection : boolean;
    isSorted : boolean;

    isFreeField : boolean;
    isAdjacent : boolean;
    isDuplicate : boolean;
}
