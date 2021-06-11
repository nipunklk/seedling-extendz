import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HateosPagedResponse } from 'extendz/core';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { BaseHttpService } from '../_base_/base.http.service';
import { Category } from './category';

export const CATEGORY_URL = 'categories';

@Injectable({ providedIn: 'root' })
export class CategoryHttpSerivce extends BaseHttpService {
  constructor(protected http: HttpClient, protected matSnackBar: MatSnackBar) {
    super(http, matSnackBar, CATEGORY_URL);
  }

  getCategoryByUrl(url: string) {
    let params = new HttpParams();
    params = params.append('url', url);
    return this.http
      .get<Category>(`${CATEGORY_URL}/search/findOneByUrl`, { params })
      .pipe(take(1));
  }

  getHomeCategories(): Observable<Category[]> {
    let params = new HttpParams();
    params = params.append('projection', 'home');
    return this.http
      .get<HateosPagedResponse>(CATEGORY_URL, { params })
      .pipe(
        take(1),
        map((c) => c._embedded[CATEGORY_URL])
      );
  }
}
