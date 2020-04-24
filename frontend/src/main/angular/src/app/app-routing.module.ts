import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { SudokugridComponent } from "./sudokugrid/sudokugrid.component";

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
      import("./sudokugrid/sudokugrid.module").then((m) => m.SudokugridModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
