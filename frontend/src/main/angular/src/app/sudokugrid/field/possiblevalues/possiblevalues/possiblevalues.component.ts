import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { SudokuFieldModel } from "src/app/sudokugrid/models/sudokufieldmodel";
import { SudokuService } from "src/app/sudokugrid/services/sudoku.service";
import { SudokuCacheService } from "src/app/sudokugrid/services/sudokucache.service";
import { PossibleValueModel } from "src/app/sudokugrid/models/possiblevaluemodel";
import { SudokuBlockModel } from "src/app/sudokugrid/models/sudokublockmodel";

@Component({
  selector: "possiblevalues",
  templateUrl: "./possiblevalues.component.html",
  styleUrls: ["./possiblevalues.component.css"]
})
export class PossibleValuesComponent implements OnInit {
  @Input()
  possibleValues: PossibleValueModel[];

  @Input()
  block: SudokuBlockModel;

  @Input()
  field: SudokuFieldModel;

  @Output()
  fieldChange: EventEmitter<SudokuFieldModel> = new EventEmitter<
    SudokuFieldModel
  >();

  constructor(
    private sudokuService: SudokuService,
    private sudokuCache: SudokuCacheService
  ) {}

  ngOnInit() {}

  valueClicked(n: PossibleValueModel) {
    // this.field.value = n.value;
    console.log("Value changed to " + n + " trigger caluclateCandidates");
    this.sudokuService
      .setFieldValue(
        this.block.x,
        this.block.y,
        this.field.x,
        this.field.y,
        n.value,
        this.sudokuCache.sudoku.getValue()
      )
      .subscribe(r => {
        this.sudokuCache.sudoku.next(r);
      });
  }
}
