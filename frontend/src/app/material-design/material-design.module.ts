// hier kommen alle benötigten imports von @angular/material hin

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// add import statements here:

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
// needed Material components get added in th exports array
@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [

    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ]
})
export class MaterialDesignModule { }
