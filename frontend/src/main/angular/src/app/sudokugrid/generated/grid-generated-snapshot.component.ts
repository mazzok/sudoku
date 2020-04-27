import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { HelpMeService } from "src/app/sudokugrid/services/sudokuhelpme.service";
import { Subscription } from "rxjs";
import { SudokuModel } from "../models/sudokumodel";
import { SudokusnapshotModel } from "../models/sudokusnapshotmodel";
import { SudokuService } from "../services/sudoku.service";
import {GeneratedSudokuSnapshotModel} from "../models/generatedsudokusnapshotmodel";
import { SudokuCacheService } from '../services/sudokucache.service';

@Component({
  selector: "grid-generated-snapshot",
  templateUrl: "./grid-generated-snapshot.component.html",
  styleUrls: ["./grid-generated-snapshot.component.css"]
})
export class GridGeneratedSnapshotComponent implements OnInit, OnDestroy {
  private subscription: Subscription = new Subscription();
  sudokuSnapshots: GeneratedSudokuSnapshotModel[];
  constructor(
    private sudokuCacheService: SudokuCacheService
  ) {}

  sudoku: SudokuModel;
  ngOnInit() {
    this.subscription.add(
      this.sudokuCacheService.sudoku.subscribe(response => {
        if (response !== null) {
          console.log("load sudoku:" + JSON.stringify(response));
          this.sudoku = response;
        }
      })
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
