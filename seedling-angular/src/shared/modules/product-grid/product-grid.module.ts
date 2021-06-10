import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ProductCardModule } from '../product-card/product-card.module';
import { ProductGridComponent } from './product-grid.component';

@NgModule({
  declarations: [ProductGridComponent],
  exports: [ProductGridComponent],
  imports: [CommonModule, FlexLayoutModule, ProductCardModule],
})
export class ProductGridModule {}
