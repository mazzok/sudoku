import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { HelpMeService } from "src/app/sudokugrid/services/sudokuhelpme.service";
import { Subscription } from "rxjs";
import { SudokuModel } from "../models/sudokumodel";
import { SudokusnapshotModel } from "../models/sudokusnapshotmodel";
import { SudokuService } from "../services/sudoku.service";

@Component({
  selector: "grid-hint",
  templateUrl: "./grid-hint.component.html",
  styleUrls: ["./grid-hint.component.css"]
})
export class GridHintComponent implements OnInit, OnDestroy {
  private subscription: Subscription = new Subscription();
  sudokuSnapshots: SudokusnapshotModel[];
  constructor(
    private helpMeService: HelpMeService,
    private sudokuService: SudokuService
  ) {}

  ngOnInit() {
    this.subscription.add(
      this.helpMeService.helpMe.subscribe(s => this.helpMeWithSudoku(s))
    );
  }

  private helpMeWithSudoku(sudokuModel: SudokuModel): void {
    this.sudokuService.nextStepForSudoku(sudokuModel).subscribe(result => {
      console.log("received data from sudoku service");
      this.sudokuSnapshots = result;
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
