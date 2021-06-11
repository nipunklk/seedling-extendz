import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BaseHttpService } from '../_base_/base.http.service';

export const PROJECT_URL = 'projects';

@Injectable({ providedIn: 'root' })
export class ProjectHttpService extends BaseHttpService {
  constructor(public http: HttpClient, public matSnackBar: MatSnackBar) {
    super(http, matSnackBar, PROJECT_URL);
  }
}
