import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { SudokugridModule } from "./sudokugrid/sudokugrid.module";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { NavbarComponent } from "./navbar/navbar.component";

@NgModule({
  imports: [BrowserModule, AppRoutingModule, SudokugridModule],

  declarations: [AppComponent, SidebarComponent, NavbarComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
