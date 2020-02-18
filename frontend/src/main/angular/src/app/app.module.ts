import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { SudokugridModule } from "./sudokugrid/sudokugrid.module";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { MainContentComponent } from "./main-content/main-content.component";
import { HelpMeService } from "./sudokugrid/services/sudokuhelpme.service";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SudokugridModule
  ],
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    MainContentComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
