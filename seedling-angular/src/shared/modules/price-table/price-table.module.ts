import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';
import { PriceTableComponent } from './price-table.component';

@NgModule({
  declarations: [PriceTableComponent],
  exports: [PriceTableComponent],
  imports: [
    CommonModule,
    FlexLayoutModule,
    RouterModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
  ],
})
export class PriceTableModule {}
