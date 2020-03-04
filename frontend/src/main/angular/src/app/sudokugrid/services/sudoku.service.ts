import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { SudokuModel } from "../models/sudokumodel";
import { SudokuFieldModel } from "../models/sudokufieldmodel";
import { JsonPipe } from "@angular/common";
import { SudokusnapshotModel } from "../models/sudokusnapshotmodel";

@Injectable()
export class SudokuService {
  constructor(private http: HttpClient) {}

  url = "sudoku";

  public getSudoku(): Observable<SudokuModel> {
    const path = this.url;
    console.log("getting Sudoku from " + path);
    return this.http.get<SudokuModel>(path);
  }

  public nextStepForSudoku(
    sudokuToSolve: SudokuModel
  ): Observable<SudokusnapshotModel[]> {
    const path = this.url + "/solve/step";
    console.log(
      "solving Sudoku at " +
        path +
        " for sudoku: " +
        JSON.stringify(sudokuToSolve)
    );
    return this.http.post<SudokusnapshotModel[]>(path, sudokuToSolve).pipe(
      map(data => {
        console.log("received data:" + JSON.stringify(data));
        return data;
      })
    );
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
