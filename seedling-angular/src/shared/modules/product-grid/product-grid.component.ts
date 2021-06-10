import { Component, Input, OnInit } from '@angular/core';
import { HateosPagedResponse } from 'extendz/core';

@Component({
  selector: 'app-product-grid',
  templateUrl: './product-grid.component.html',
  styleUrls: ['./product-grid.component.scss'],
})
export class ProductGridComponent implements OnInit {
  
  @Input() page: HateosPagedResponse;

  constructor() {}

  ngOnInit(): void {}
}
