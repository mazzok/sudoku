import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { SudokugridRoutingModule } from "./sudokugrid-routing.module";
import { GridComponent } from "./grid/grid.component";
import { SudokuService } from "./services/sudoku.service";
import { BlockComponent } from "./block/block.component";
import { FieldComponent } from "./field/field.component";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { ButtonsModule } from "ngx-bootstrap/buttons";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { PossibleValuesComponent } from "./field/possiblevalues/possiblevalues/possiblevalues.component";
import { GridHintComponent } from "./grid-hint/grid-hint.component";
import { HelpMeService } from "./services/sudokuhelpme.service";
import { GridGeneratedSnapshotComponent } from "./generated/grid-generated-snapshot.component";
import { SudokugridComponent } from "./sudokugrid.component";
import { MainContentComponent } from "../main-content/main-content.component";

@NgModule({
  declarations: [
    SudokugridComponent,
    GridComponent,
    GridHintComponent,
    GridGeneratedSnapshotComponent,
    MainContentComponent,
    BlockComponent,
    FieldComponent,
    PossibleValuesComponent,
  ],
  imports: [
    SudokugridRoutingModule,
    CommonModule,
    BsDropdownModule,
    ButtonsModule,
    // BrowserAnimationsModule,
  ],
  providers: [SudokuService, HelpMeService],
})
export class SudokugridModule {}
