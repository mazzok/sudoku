import { Component, OnInit, Input } from "@angular/core";
import { SudokuFieldModel } from "../models/sudokufieldmodel";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuSnapshotFieldModel } from "../models/sudokusnapshotfieldmodel";
import { SudokuSnapshotBlockModel } from "../models/sudokusnapshotblockmodel";

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
}
