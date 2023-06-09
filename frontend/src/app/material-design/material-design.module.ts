// hier kommen alle benötigten imports von @angular/material hin

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// add import statements here:

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTreeModule } from '@angular/material/tree';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatExpansionModule } from '@angular/material/expansion';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
// needed Material components get added in th exports array
@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [

    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTreeModule,
    MatCardModule,
    MatFormFieldModule,
    MatExpansionModule,
    MatSelectModule,
    MatInputModule,

  ]
})
export class MaterialDesignModule {}
