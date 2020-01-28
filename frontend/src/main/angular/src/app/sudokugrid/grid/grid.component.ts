import { Component, OnInit, OnDestroy } from "@angular/core";
import { SudokuService } from "../services/sudoku.service";
import { SudokuModel } from "../models/sudokumodel";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuCacheService } from "../services/sudokucache.service";
import { Subscription } from "rxjs";

@Component({
  selector: "grid",
  templateUrl: "./grid.component.html",
  styleUrls: ["./grid.component.css"]
})
export class GridComponent implements OnInit, OnDestroy {
  constructor(private sudokuCacheService: SudokuCacheService) {}

  sudoku: SudokuModel;
  xBlocks: string = "";
  yBlocks: string = "";

  subscription: Subscription = new Subscription();

  ngOnInit() {
    this.subscription.add(
      this.sudokuCacheService.sudoku.subscribe(response => {
        if (response !== null) {
          console.log("grid component load sudoku:" + response);
          this.sudoku = response;
          this.initBlockGridProperties();
        }
      })
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  initBlockGridProperties() {
    if (this.xBlocks.length === 0 || this.yBlocks.length === 0) {
      let x: number = 0;
      let y: number = 0;

      while (x < this.sudoku.xDim) {
        this.xBlocks = this.xBlocks + "1fr ";
        x++;
      }

      while (y < this.sudoku.yDim) {
        this.yBlocks = this.yBlocks + "1fr ";
        y++;
      }
    }
  }
}
