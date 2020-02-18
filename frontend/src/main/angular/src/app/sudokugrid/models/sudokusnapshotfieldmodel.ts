import { PossibleValueModel } from "./possiblevaluemodel";

export interface SudokuSnapshotFieldModel {
  x: number;
  y: number;
  value: number;
  isReactor: boolean;
  isActor: boolean;
  possibleValues: PossibleValueModel[];
}
