import { Injectable, EventEmitter } from "@angular/core";
import { SudokuService } from "./sudoku.service";
import { SudokuFieldModel } from "../models/sudokufieldmodel";
import { SudokuModel } from "../models/sudokumodel";
import { BehaviorSubject } from "rxjs";
import { GeneratedSudokuSnapshotBlockModel } from '../models/generatedsudokusnapshotblockmodel';
import { GeneratedSudokuSnapshotModel } from '../models/generatedsudokusnapshotmodel';
import { GeneratedSudokuModel } from '../models/generatedsudokumodel';

@Injectable({
  providedIn: "root"
})
export class SudokuCacheService {
  public sudoku: BehaviorSubject<SudokuModel> = new BehaviorSubject(null);

  public generatedsudoku: BehaviorSubject<GeneratedSudokuModel> = new BehaviorSubject(null);

  errors: string;
  constructor(private sudokuService: SudokuService) {
    this.sudokuService.getSudoku().subscribe(
      response => {
        console.log(response);
        this.errors = "";
        this.sudoku.next(response);
      },
      errors => (this.errors = errors)
    );

    this.sudokuService.getGeneratedSudoku().subscribe(
      response => {
        console.log(response);
        this.errors = "";
        this.generatedsudoku.next(response);
      },
      errors => (this.errors = errors)
    );
  }
}
