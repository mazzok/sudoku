import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { HelpMeService } from "./sudokugrid/services/sudokuhelpme.service";
import { SudokugridModule } from "./sudokugrid/sudokugrid.module";

@NgModule({
  imports: [AppRoutingModule, BrowserModule, HttpClientModule],
  declarations: [AppComponent],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
