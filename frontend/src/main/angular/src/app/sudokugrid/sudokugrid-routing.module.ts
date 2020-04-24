import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { GridComponent } from "./grid/grid.component";
import { MainContentComponent } from "../main-content/main-content.component";
import { GridGeneratedSnapshotComponent } from "./generated/grid-generated-snapshot.component";
import { SudokugridComponent } from "./sudokugrid.component";

const routes: Routes = [
  {
    path: "",
    component: SudokugridComponent,
    children: [
      { path: "", redirectTo: "generatesudoku", pathMatch: "full" },
      {
        path: "sudoku",
        component: SudokugridComponent,
      },
      {
        path: "generatesudoku",
        component: GridGeneratedSnapshotComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SudokugridRoutingModule {}
