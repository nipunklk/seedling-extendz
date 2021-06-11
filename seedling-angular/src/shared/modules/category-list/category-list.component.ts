import { Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { Category } from 'src/shared/api/category/category';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss'],
})
export class CategoryListComponent implements OnChanges {
  @Input() categories: Category[];

  @ViewChild('category') categoryMenuTrigger: MatMenuTrigger;

  constructor() {}
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['categories'] && changes['categories'].currentValue) this.openMenu();
  }

  openMenu() {
    // this.categoryMenuTrigger.openMenu();
  }
}
