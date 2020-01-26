import { Component, OnInit, Input } from "@angular/core";
import { SudokuBlockModel } from "../models/sudokublockmodel";
import { SudokuFieldModel } from "../models/sudokufieldmodel";

@Component({
  selector: "block",
  templateUrl: "./block.component.html",
  styleUrls: ["./block.component.css"]
})
export class BlockComponent implements OnInit {
  yFields: string = "";
  xFields: string = "";

  @Input()
  block: SudokuBlockModel;

  constructor() {}

  ngOnInit() {
    this.initFieldGridProperties();
  }

  initFieldGridProperties() {
    let x: number = 0;
    let y: number = 0;

    while (x < this.block.xDim) {
      this.xFields = this.xFields + "1fr ";
      x++;
    }

    while (y < this.block.yDim) {
      this.yFields = this.yFields + "1fr ";
      y++;
    }
  }

}
