import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { CategorySelectComponent } from './category-select.component';

@NgModule({
  declarations: [CategorySelectComponent],
  exports: [CategorySelectComponent],
  imports: [CommonModule, MatListModule],
})
export class CategorySelectModule {}
