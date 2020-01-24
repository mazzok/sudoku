import { Component, OnInit, Input } from "@angular/core";
import { SudokuFieldModel } from "../models/sudokufieldmodel";

@Component({
  selector: "field",
  templateUrl: "./field.component.html",
  styleUrls: ["./field.component.css"]
})
export class FieldComponent implements OnInit {
  @Input()
  field: SudokuFieldModel;

  constructor() {}

  ngOnInit() {}
}
