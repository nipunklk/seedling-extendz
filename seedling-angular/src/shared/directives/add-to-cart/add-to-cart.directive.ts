import { Directive, Input, OnInit } from '@angular/core';
import { CartItem } from 'src/shared/state/cart/cart-item';

@Directive({
  selector: '[appAddToCart]',
})
export class AddToCartDirective implements OnInit {
  @Input('cartItem') cartItem: CartItem;
  constructor() {}

  ngOnInit() {
    console.log(this.cartItem);
  }
}
