import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { SudokuModel } from "../models/sudokumodel";

@Injectable()
export class SudokuService {
  constructor(private http: HttpClient) {}

  url = "sudoku";

  public getSudoku(): Observable<SudokuModel> {
    console.log("getting Sudoku from " + this.url);
    return this.http.get<SudokuModel>(this.url);
  }

  public calculateCandidates(sudoku: SudokuModel): Observable<SudokuModel> {
    console.log("in calculateCandidates");
    return this.http
      .post<SudokuModel>(this.url + "/calculatecandidates", sudoku)
      .pipe(
        map(data => {
          console.log("received data:" + data);
          return data;
        })
      );
  }
}
