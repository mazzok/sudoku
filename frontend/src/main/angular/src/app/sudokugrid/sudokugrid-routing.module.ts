import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { GridComponent } from "./grid/grid.component";
import { MainContentComponent } from "../main-content/main-content.component";

const routes: Routes = [
  {
    path: "sudoku",
    component: MainContentComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SudokugridRoutingModule {}
