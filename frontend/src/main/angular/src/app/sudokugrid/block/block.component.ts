import { Component, OnInit, Input } from "@angular/core";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuFieldModel } from "../models/sudokufieldmodel";
import { SudokuSnapshotBlockModel } from "../models/sudokusnapshotblockmodel";

@Component({
  selector: "block",
  templateUrl: "./block.component.html",
  styleUrls: ["./block.component.css"]
})
export class BlockComponent implements OnInit {
  yFields: string = "";
  xFields: string = "";

  blockModel: SudokuBlockModel;
  blockSnapshotModel: SudokuSnapshotBlockModel;

  @Input()
  set block(_block: SudokuBlockModel) {
    this.blockModel = _block;
    this.initFieldGridProperties(this.blockModel.xDim, this.blockModel.yDim);
  }

  @Input()
  set snapshotblock(_snapshotblock: SudokuSnapshotBlockModel) {
    this.initFieldGridProperties(_snapshotblock.xDim, _snapshotblock.yDim);
    this.blockSnapshotModel = _snapshotblock;
  }

  constructor() {}

  ngOnInit() {}

  initFieldGridProperties(xDim: number, yDim: number) {
    if (this.xFields.length === 0 || this.yFields.length === 0) {
      let x: number = 0;
      let y: number = 0;

      while (x < xDim) {
        this.xFields = this.xFields + "1fr ";
        x++;
      }

      while (y < yDim) {
        this.yFields = this.yFields + "1fr ";
        y++;
      }

      console.log(
        "setting block field grid properties to xFields:" +
          this.xFields +
          " yFields:" +
          this.yFields
      );
    }
  }
}
