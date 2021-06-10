import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HateosPagedResponse } from 'extendz/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { BaseHttpService } from '../_base_/base.http.service';
import { Product } from './product';

export const PRODUCTS_URL = 'products';

@Injectable({ providedIn: 'root' })
export class ProductHttpSerivce extends BaseHttpService {
  constructor(protected http: HttpClient, protected matSnackBar: MatSnackBar) {
    super(http, matSnackBar, PRODUCTS_URL);
  }

  getOneByUrl(url: string): Observable<Product> {
    let params = new HttpParams();
    params = params.append('url', url);
    params = params.append('projection', 'entity');
    return this.http
      .get<Product>(`${PRODUCTS_URL}/search/findOneByUrl`, { params })
      .pipe(take(1));
  }

  getAllByCategoryUrl(url: string) {
    let params = new HttpParams();
    params = params.append('categories.url', url);
    params = params.append('projection', 'entity');
    return this.http
      .get<Product>(`${PRODUCTS_URL}`, { params })
      .pipe(take(1));
  }

  getAllByCategoryUrlAndSubCategoryUrls(category: string, subcategories: string[]) {
    let params = new HttpParams();
    params = params.append('categories.url', category);
    if (typeof subcategories == 'string') subcategories = [subcategories];

    if (subcategories)
      subcategories.forEach((s) => (params = params.append('subCategories.url', s)));
    params = params.append('projection', 'entity');
    return this.http
      .get<Product>(`${PRODUCTS_URL}`, { params })
      .pipe(take(1));
  }

  findAllByName(name: string) {
    let params = new HttpParams();
    params = params.append('name', name);
    return this.http
      .get<HateosPagedResponse>(`${PRODUCTS_URL}`, { params })
      .pipe(take(1));
  }
}
