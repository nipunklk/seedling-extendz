import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatRippleModule } from '@angular/material/core';
import { RouterModule } from '@angular/router';
import { AppDireactivesModule } from 'src/shared/directives/direactives.module';
import { ProductCardComponent } from './product-card.component';

@NgModule({
  declarations: [ProductCardComponent],
  exports: [ProductCardComponent],
  imports: [
    CommonModule,
    FlexLayoutModule, //
    AppDireactivesModule,
    RouterModule,
    MatCardModule,
    MatRippleModule,
  ],
})
export class ProductCardModule {}
