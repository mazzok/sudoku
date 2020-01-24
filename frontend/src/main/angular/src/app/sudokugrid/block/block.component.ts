import { Component, OnInit, Input } from "@angular/core";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuFieldModel } from "../models/sudokufieldmodel";

@Component({
  selector: "block",
  templateUrl: "./block.component.html"
})
export class BlockComponent implements OnInit {
  @Input()
  block: SudokuBlockModel;

  blockGrid: SudokuFieldModel[][];

  constructor() {}

  ngOnInit() {
    this.initBlocks();
  }

  initBlocks() {
    let i = 0;
    let x = 0;
    let y = 0;
    this.blockGrid = [];
    console.log(" this block field size" + this.block.sudokuFields.length);

    while (i < this.block.sudokuFields.length) {
      if (x >= this.block.xDim) {
        x = 0;
        y++;
      }

      if (this.blockGrid[y] === undefined) {
        this.blockGrid[y] = [];
      }

      this.blockGrid[y][x] = this.block.sudokuFields[i];
      console.log("y:" + y + " x:" + x);

      x++;
      i++;
    }
  }
}
