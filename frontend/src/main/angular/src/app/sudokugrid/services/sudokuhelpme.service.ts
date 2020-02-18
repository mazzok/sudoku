import { Injectable, EventEmitter } from "@angular/core";
import { SudokuModel } from "../models/sudokumodel";

@Injectable({
  providedIn: "root"
})
export class HelpMeService {
  helpMe: EventEmitter<SudokuModel> = new EventEmitter();

  constructor() {}
}
