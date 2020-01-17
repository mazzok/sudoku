import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { GridComponent } from "./grid/grid.component";

const routes: Routes = [
  {
    path: "sudoku",
    component: GridComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SudokugridRoutingModule {}
