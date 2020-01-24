import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { SudokugridRoutingModule } from "./sudokugrid-routing.module";
import { GridComponent } from "./grid/grid.component";
import { SudokuService } from "./services/sudoku.service";
import { BlockComponent } from "./block/block.component";
import { FieldComponent } from "./field/field.component";

@NgModule({
  declarations: [GridComponent, BlockComponent, FieldComponent],
  imports: [CommonModule, SudokugridRoutingModule],
  providers: [SudokuService]
})
export class SudokugridModule {}
