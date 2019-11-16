import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as Material from "@angular/material";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    Material.MatDialogModule
  ],
  exports: [
    Material.MatDialogModule
  ],
})
export class MaterialModule { }
