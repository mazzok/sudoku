import { PossibleValueModel } from "./possiblevaluemodel";

export interface SudokuFieldModel {
  x: number;
  y: number;
  value: number;
  possibleValues: PossibleValueModel[];
}
