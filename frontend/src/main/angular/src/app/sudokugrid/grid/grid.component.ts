import { Component, OnInit } from "@angular/core";
import { SudokuService } from "../services/sudoku.service";
import { SudokuModel } from "../models/sudokumodel";
import { SudokuBlockModel } from "../models/sudokublockmodel";

@Component({
  selector: "grid",
  templateUrl: "./grid.component.html",
  styleUrls: ["./grid.component.css"]
})
export class GridComponent implements OnInit {
  constructor(private sudokuService: SudokuService) {}

  sudoku: SudokuModel;
  xBlocks: string = "";
  yBlocks: string = "";

  errors: string;

  ngOnInit() {
    this.sudokuService.getSudoku().subscribe(
      response => {
        this.sudoku = response;
        this.initBlockGridProperties();
        this.errors = "";
      },
      errors => (this.errors = errors)
    );
  }

  initBlockGridProperties() {
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
