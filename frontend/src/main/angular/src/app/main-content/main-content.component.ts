import { Component, OnInit } from "@angular/core";
import { HelpMeService } from "../sudokugrid/services/sudokuhelpme.service";
import { SudokuService } from "../sudokugrid/services/sudoku.service";
import { SudokuCacheService } from "../sudokugrid/services/sudokucache.service";

@Component({
  selector: "app-main-content",
  templateUrl: "./main-content.component.html",
  styleUrls: ["./main-content.component.css"]
})
export class MainContentComponent implements OnInit {
  constructor(
    private helpMeService: HelpMeService,
    private sudokuCache: SudokuCacheService
  ) {}

  ngOnInit() {}

  helpMeClicked(): void {
    console.log("help me clicked!");
    this.helpMeService.helpMe.next(this.sudokuCache.sudoku.getValue());
  }
}
