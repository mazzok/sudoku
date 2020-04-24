import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { HelpMeService } from "src/app/sudokugrid/services/sudokuhelpme.service";
import { Subscription } from "rxjs";
import { SudokuModel } from "../models/sudokumodel";
import { SudokusnapshotModel } from "../models/sudokusnapshotmodel";
import { SudokuService } from "../services/sudoku.service";
import {GeneratedSudokusnapshotModel} from "../models/generatedsudokusnapshotmodel";

@Component({
  selector: "grid-generated-snapshot",
  templateUrl: "./grid-generated-snapshot.component.html",
  styleUrls: ["./grid-generated-snapshot.component.css"]
})
export class GridGeneratedSnapshotComponent implements OnInit, OnDestroy {
  private subscription: Subscription = new Subscription();
  sudokuSnapshots: GeneratedSudokusnapshotModel[];
  constructor(
    private helpMeService: HelpMeService,
    private sudokuService: SudokuService
  ) {}

  ngOnInit() {

  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
