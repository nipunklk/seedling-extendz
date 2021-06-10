import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AddToCartDirective } from './add-to-cart/add-to-cart.directive';
import { BrandDirective } from './brand/brand.directive';
import { CategoryDirective } from './category/category.directive';
import { DecreaseQuantityDirective } from './decrease-quantity/decrease-quantity.directive';
import { IncreaseQuantityDirective } from './increase-quantity/increase-quantity.directive';
import { MaterialElevationDirective } from './material-elevation/material-elevation.directive';
import { RemoveFromCartDirective } from './remove-from-cart/remove-from-cart.directive';
import { ProductDirective } from './product/product.directive';

@NgModule({
  declarations: [
    AddToCartDirective,
    IncreaseQuantityDirective,
    DecreaseQuantityDirective,
    RemoveFromCartDirective,
    BrandDirective,
    CategoryDirective,
    MaterialElevationDirective,
    ProductDirective,
  ],
  exports: [
    AddToCartDirective,
    IncreaseQuantityDirective,
    DecreaseQuantityDirective,
    RemoveFromCartDirective,
    BrandDirective,
    CategoryDirective,
    ProductDirective,
    MaterialElevationDirective,
  ],
  imports: [CommonModule],
})
export class AppDireactivesModule {}
