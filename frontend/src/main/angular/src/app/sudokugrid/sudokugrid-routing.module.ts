import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { GridComponent } from "./grid/grid.component";
import { GridGeneratedSnapshotComponent } from "./generated/grid-generated-snapshot.component";
import { SudokugridComponent } from "./sudokugrid.component";
import { MainContentComponent } from './main-content/main-content.component';

const routes: Routes = [
  {
    path: "",
    component: SudokugridComponent,
    children: [
      { path: "", redirectTo: "sudoku", pathMatch: "full" },
      {
        path: "sudoku",
        component: MainContentComponent,
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
