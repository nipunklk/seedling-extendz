import { Directive, HostListener, Input } from '@angular/core';
import { CartItem } from 'src/shared/state/cart/cart-item';
import { CartState } from 'src/shared/state/cart/cart.state';

@Directive({
  selector: '[appDecreaseQuantity]',
})
export class DecreaseQuantityDirective {
  @Input('cartItem') cartItem: CartItem;

  @HostListener('click') onClick() {
    this.cartState.decreaseQuantity(this.cartItem);
  }

  constructor(private cartState: CartState) {}
}
