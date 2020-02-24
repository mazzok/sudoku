import { Component, OnInit, Input } from "@angular/core";
import { SudokuFieldModel } from "../models/sudokufieldmodel";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuSnapshotFieldModel } from "../models/sudokusnapshotfieldmodel";
import { SudokuSnapshotBlockModel } from "../models/sudokusnapshotblockmodel";
import { PossibleValueModel } from "../models/possiblevaluemodel";

@Component({
  selector: "field",
  templateUrl: "./field.component.html",
  styleUrls: ["./field.component.css"]
})
export class FieldComponent implements OnInit {
  @Input()
  field: SudokuFieldModel;

  @Input()
  block: SudokuBlockModel;

  @Input()
  snapshotfield: SudokuSnapshotFieldModel;

  @Input()
  snapshotblock: SudokuSnapshotBlockModel;

  constructor() {}

  ngOnInit() {}

  onFieldClick(): void {
    console.log("clicked on field " + this.field.x + " , " + this.field.y);
  }

  getIsActorFieldValue(): string {
    if (this.snapshotfield.isValueReserved) {
      return this.possibleValuesToString(this.snapshotfield.possibleValues);
    }
    return this.snapshotfield.value !== null
      ? this.snapshotfield.value.toString()
      : "";
  }

  getIsReactorFieldValue(): string {
    if (this.snapshotfield.isValueReserved) {
      return this.possibleValuesToString(this.snapshotfield.possibleValues);
    }
    return this.snapshotfield.value !== null
      ? this.snapshotfield.value.toString()
      : "";
  }

  getDefaultFieldValue(): string {
    if (this.snapshotfield !== undefined) {
      if (this.snapshotfield.isValueReserved) {
        return this.possibleValuesToString(this.snapshotfield.possibleValues);
      }
      return this.snapshotfield.value !== null
        ? this.snapshotfield.value.toString()
        : "";
    } else if (this.field !== undefined) {
      return this.field.value !== null ? this.field.value.toString() : "";
    }

    return "UNKNOWN";
  }

  getInitialFieldValue(): string {
    return this.field.value !== null ? this.field.value.toString() : "";
  }

  private possibleValuesToString(possibleValues: PossibleValueModel[]): string {
    let possibleValueConcat = "";
    let filteredPossibleValues: PossibleValueModel[] = possibleValues.filter(
      p => p.isHidden === false
    );
    for (let i = 0; i < filteredPossibleValues.length; i++) {
      possibleValueConcat =
        possibleValueConcat + filteredPossibleValues[i].value.toString();
      if (i < filteredPossibleValues.length - 1) {
        possibleValueConcat = possibleValueConcat + " / ";
      }
    }
    return possibleValueConcat;
  }
}
