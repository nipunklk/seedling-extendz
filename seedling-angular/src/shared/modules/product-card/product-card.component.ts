import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/shared/api/product/product';
import { AppState } from 'src/shared/state/app/app.state';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss'],
})
export class ProductCardComponent implements OnInit {
  @Input() product: Product;

  constructor(public appState: AppState) {}

  ngOnInit(): void {}
}
