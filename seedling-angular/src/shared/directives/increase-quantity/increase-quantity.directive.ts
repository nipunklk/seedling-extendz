import { Directive, HostListener, Input, OnInit } from '@angular/core';
import { CartItem } from 'src/shared/state/cart/cart-item';
import { CartState } from 'src/shared/state/cart/cart.state';

@Directive({
  selector: '[appIncreaseQuantity]',
})
export class IncreaseQuantityDirective {
  @Input('cartItem') cartItem: CartItem;

  @HostListener('click') onClick() {
    this.cartState.increaseQuantity(this.cartItem);
  }

  constructor(private cartState: CartState) {}
}
