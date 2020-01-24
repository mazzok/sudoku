import { Component, OnInit, Input } from "@angular/core";
import { SudokuBlockModel } from "../models/sudokublockmodel";

@Component({
  selector: "block",
  templateUrl: "./block.component.html"
})
export class BlockComponent implements OnInit {
  @Input()
  block: SudokuBlockModel;

  constructor() {}

  ngOnInit() {}
}
