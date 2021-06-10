import { Directive, HostListener, Input } from '@angular/core';
import { CartItem } from 'src/shared/state/cart/cart-item';
import { CartState } from 'src/shared/state/cart/cart.state';

@Directive({
  selector: '[appRemoveFromCart]',
})
export class RemoveFromCartDirective {
  @Input('cartItem') cartItem: CartItem;

  @HostListener('click') onClick() {
    this.cartState.remove(this.cartItem);
  }

  constructor(private cartState: CartState) {}
}
