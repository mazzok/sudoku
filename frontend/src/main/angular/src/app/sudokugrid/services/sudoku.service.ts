import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { SudokuModel } from "../models/sudokumodel";

@Injectable()
export class SudokuService {
  constructor(private http: HttpClient) {}

  url = "hello";

  public getSudoku(): Observable<SudokuModel> {
    console.log("getting Sudoku from " + this.url);
    return this.http.get<SudokuModel>(this.url);
  }
}
