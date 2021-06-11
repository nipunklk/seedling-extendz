import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { debounceTime, mergeMap, startWith } from 'rxjs/operators';
import { Product } from 'src/shared/api/product/product';
import { AppState } from 'src/shared/state/app/app.state';
import { ProductState } from 'src/shared/state/product/prodcut.state';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss'],
})
export class SearchBarComponent implements OnInit {
  formGroup: FormGroup;
  products$: Observable<Product[]>;

  constructor(
    private fb: FormBuilder,
    private productState: ProductState,
    private router: Router,
    private appState: AppState
  ) {
    this.formGroup = this.fb.group({
      searchText: [null],
    });
    this.products$ = this.formGroup.controls['searchText'].valueChanges.pipe(
      startWith(''),
      debounceTime(100),
      mergeMap((value: string) => this.productState.search(value))
    );
  }

  ngOnInit(): void {}

  displayFn(product: Product) {
    return product?.name;
  }

  onSelect(e: MatAutocompleteSelectedEvent) {
    this.appState.loading(true);
    const prodcut: Product = e.option.value;
    this.router.navigate(['products', prodcut.url]);
  }

  clear() {
    this.formGroup.patchValue({ searchText: '' });
  }
}
