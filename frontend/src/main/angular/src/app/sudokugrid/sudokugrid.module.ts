import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { SudokugridRoutingModule } from "./sudokugrid-routing.module";
import { GridComponent } from "./grid/grid.component";

@NgModule({
  declarations: [GridComponent],
  imports: [CommonModule, SudokugridRoutingModule]
})
export class SudokugridModule {}
