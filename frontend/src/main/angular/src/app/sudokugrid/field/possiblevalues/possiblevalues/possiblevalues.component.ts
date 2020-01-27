import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { SudokuFieldModel } from "src/app/sudokugrid/models/sudokufieldmodel";

@Component({
  selector: "possiblevalues",
  templateUrl: "./possiblevalues.component.html",
  styleUrls: ["./possiblevalues.component.css"]
})
export class PossibleValuesComponent implements OnInit {
  @Input()
  possibleValues: number[];

  @Input()
  field: SudokuFieldModel;

  @Output()
  fieldChange: EventEmitter<SudokuFieldModel> = new EventEmitter<
    SudokuFieldModel
  >();

  constructor() {}

  ngOnInit() {}

  valueClicked(n: number) {
    this.field.value = n;
    this.fieldChange.emit(this.field);
  }
}
