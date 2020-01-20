import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { SudokugridRoutingModule } from "./sudokugrid-routing.module";
import { GridComponent } from "./grid/grid.component";
import { SudokuService } from "./services/sudoku.service";

@NgModule({
  declarations: [GridComponent],
  imports: [CommonModule, SudokugridRoutingModule],
  providers: [SudokuService]
})
export class SudokugridModule {}
