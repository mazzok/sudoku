import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { SudokuService } from "../services/sudoku.service";
import { SudokuModel } from "../models/sudokumodel";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuCacheService } from "../services/sudokucache.service";
import { Subscription } from "rxjs";
import { SudokusnapshotModel } from "../models/sudokusnapshotmodel";

@Component({
  selector: "grid",
  templateUrl: "./grid.component.html",
  styleUrls: ["./grid.component.css"]
})
export class GridComponent implements OnInit {
  constructor(private sudokuCacheService: SudokuCacheService) {}

  sudokumodel: SudokuModel;
  sudokusnapshotmodel: SudokusnapshotModel;

  @Input()
  set sudoku(_sudoku: SudokuModel) {
    this.initBlockGridProperties(_sudoku.xDim, _sudoku.yDim);
    this.sudokumodel = _sudoku;
  }

  @Input()
  set sudokusnapshot(_sudokusnapshot: SudokusnapshotModel) {
    this.initBlockGridProperties(_sudokusnapshot.xDim, _sudokusnapshot.yDim);
    this.sudokusnapshotmodel = _sudokusnapshot;
  }

  xBlocks: string = "";
  yBlocks: string = "";

  ngOnInit() {}

  initBlockGridProperties(xDim: number, yDim: number) {
    if (this.xBlocks.length === 0 || this.yBlocks.length === 0) {
      let x: number = 0;
      let y: number = 0;

      while (x < xDim) {
        this.xBlocks = this.xBlocks + "1fr ";
        x++;
      }

      while (y < yDim) {
        this.yBlocks = this.yBlocks + "1fr ";
        y++;
      }
    }

    console.log(
      "setting grid field grid properties to xBlocks:" +
        this.xBlocks +
        " yBlocks:" +
        this.yBlocks
    );
  }
}
