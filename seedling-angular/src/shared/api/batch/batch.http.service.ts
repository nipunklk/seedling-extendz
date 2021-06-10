import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BaseHttpService } from '../_base_/base.http.service';

export const BATCH_URL = 'batches';

@Injectable({ providedIn: 'root' })
export class BatchHttpSerivce extends BaseHttpService {
  constructor(protected http: HttpClient, protected matSnackBar: MatSnackBar) {
    super(http, matSnackBar, BATCH_URL);
  }
}
