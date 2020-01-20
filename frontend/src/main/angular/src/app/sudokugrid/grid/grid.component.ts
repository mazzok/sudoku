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
  sudokuGrid: SudokuBlockModel[][];
  errors: string;

  ngOnInit() {
    this.sudokuService.getSudoku().subscribe(
      response => {
        this.sudoku = response;
        this.initBlocks();
        this.errors = "";
      },
      errors => (this.errors = errors)
    );
  }

  initBlocks() {
    let i = 0;
    let x = 0;
    let y = 0;
    this.sudokuGrid = [];
    while (i < this.sudoku.sudokuBlocks.length) {
      if (x < this.sudoku.xDim) {
        this.sudokuGrid[y][x] = this.sudoku.sudokuBlocks[i];
        console.log("y:" + y + " x:" + x);
        x++;
      } else {
        x = 0;
        y++;
      }
      i++;
    }
  }
}
