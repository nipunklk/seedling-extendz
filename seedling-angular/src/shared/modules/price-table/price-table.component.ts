import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { Component, Input, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from 'src/shared/api/product/product';
import { StockKeepingUnit } from 'src/shared/api/stock-keeping-unit/stock-keeping-unit';
import { CartState } from 'src/shared/state/cart/cart.state';

@Component({
  selector: 'app-price-table',
  templateUrl: './price-table.component.html',
  styleUrls: ['./price-table.component.scss'],
})
export class PriceTableComponent implements OnInit {
  @Input() product: Product;

  skuDataSource: SkuDataSource;
  displayedColumns: string[] = ['price', 'cartQuantity'];

  constructor(private cartState: CartState) {}

  ngOnInit(): void {
    // Dynamic table
    this.product.stockKeepingUnits.forEach((sku) => {
      if (sku.color && this.displayedColumns.indexOf('color') < 0)
        this.displayedColumns.splice(1, 0, 'color');
      if (sku.size && this.displayedColumns.indexOf('size') < 0)
        this.displayedColumns.splice(1, 0, 'size');

      if (sku.capacity && this.displayedColumns.indexOf('capacity') < 0)
        this.displayedColumns.splice(1, 0, 'capacity');
      sku.cartQuantity = 0;
    });
    const x = this.product.stockKeepingUnits.sort((a, b) =>
      a.variety > b.variety ? 1 : b.variety > a.variety ? -1 : 0 || a.price.value - b.price.value
    );
    this.skuDataSource = new SkuDataSource(x);
  }

  add(sku: StockKeepingUnit) {
    const copy = this.skuDataSource.data().slice();
    sku.cartQuantity = ++sku.cartQuantity;
    this.skuDataSource.update(copy);
  }

  addToCart(product: Product) {
    const data = this.skuDataSource
      .data()
      .filter((s) => s.cartQuantity > 0)
      .forEach((stockKeepingUnit) => {
        this.cartState.addToCart({
          stockKeepingUnit,
          product,
          quantity: stockKeepingUnit.cartQuantity,
        });
      });
  }
}

export class SkuDataSource extends DataSource<StockKeepingUnit> {
  private dataSubject = new BehaviorSubject<StockKeepingUnit[]>([]);

  constructor(data: StockKeepingUnit[]) {
    super();
    this.dataSubject.next(data);
  }

  data() {
    return this.dataSubject.value;
  }

  update(data: StockKeepingUnit[]) {
    this.dataSubject.next(data);
  }

  connect(collectionViewer: CollectionViewer): Observable<StockKeepingUnit[]> {
    return this.dataSubject;
  }

  disconnect(collectionViewer: CollectionViewer): void {}
}
