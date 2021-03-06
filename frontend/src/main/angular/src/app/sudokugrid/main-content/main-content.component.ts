import { Component, OnInit, OnDestroy } from "@angular/core";
import { HelpMeService } from '../services/sudokuhelpme.service';
import { SudokuCacheService } from '../services/sudokucache.service';
import { Subscription } from 'rxjs';
import { SudokuModel } from '../models/sudokumodel';

@Component({
  selector: "app-main-content",
  templateUrl: "./main-content.component.html",
  styleUrls: ["./main-content.component.css"]
})
export class MainContentComponent implements OnInit, OnDestroy {
  constructor(
    private helpMeService: HelpMeService,
    private sudokuCacheService: SudokuCacheService
  ) {}

  subscription: Subscription = new Subscription();
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

  helpMeClicked(): void {
    console.log("help me clicked!");
    this.helpMeService.helpMe.next(this.sudoku);
  }
}
