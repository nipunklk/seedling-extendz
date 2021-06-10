import { Directive } from '@angular/core';
import { CartState } from '../state/cart/cart.state';

@Directive({
  selector: '[appAddToCart]',
})
export class AddToCartDirective {
  constructor(private cartState: CartState) {}
}
