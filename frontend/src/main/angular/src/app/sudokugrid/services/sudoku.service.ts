import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { SudokuModel } from "../models/sudokumodel";
import { SudokuFieldModel } from "../models/sudokufieldmodel";

@Injectable()
export class SudokuService {
  constructor(private http: HttpClient) {}

  url = "sudoku";

  public getSudoku(): Observable<SudokuModel> {
    console.log("getting Sudoku from " + this.url);
    return this.http.get<SudokuModel>(this.url);
  }

  public setFieldValue(
    blockX: number,
    blockY: number,
    fieldX: number,
    fieldY: number,
    fieldValue: number,
    sudoku: SudokuModel
  ): Observable<SudokuModel> {
    console.log("in setfieldvalue");

    var o = {
      blockX: blockX,
      blockY: blockY,
      fieldX: fieldX,
      fieldY: fieldY,
      value: fieldValue,
      sudokuModel: sudoku
    };

    return this.http.post<SudokuModel>(this.url + "/setfieldvalue", o).pipe(
      map(data => {
        console.log("received data:" + data);
        return data;
      })
    );
  }
}
