import { Pipe, PipeTransform } from '@angular/core';
import { getId } from 'extendz/core';
import { Href } from 'extendz/core/models/hateos/href';
import { Price } from 'src/shared/api/stock-keeping-unit/stock-keeping-unit';

@Pipe({
  name: 'price',
})
export class PricePipe implements PipeTransform {
  transform(price: Price, ...args: unknown[]): unknown {
    // const currency = price.
    const link = price._links.currency as Href;
    const id = getId(link.href);
    return `${price.value} ${id}`;
  }
}
