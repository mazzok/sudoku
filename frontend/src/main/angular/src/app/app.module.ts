import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { SudokugridModule } from "./sudokugrid/sudokugrid.module";

@NgModule({
  imports: [BrowserModule, AppRoutingModule, SudokugridModule],

  declarations: [AppComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
