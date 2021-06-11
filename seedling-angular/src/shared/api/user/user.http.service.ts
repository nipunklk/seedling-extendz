import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { throwError } from 'rxjs';
import { catchError, take } from 'rxjs/operators';
import { AccessTokenResponse } from '../../utils/access-token-response';
import { PUBLIC_HEADER } from '../_base_/base.http.service';
import { UserLoginDto } from './user';

export const USER_URL = 'signin';

@Injectable({ providedIn: 'root' })
export class UserHttpService {
  constructor(private http: HttpClient, private matSnackBar: MatSnackBar) {}

  public login(dto: UserLoginDto) {
    return this.http.post<AccessTokenResponse>(`${USER_URL}`, dto, PUBLIC_HEADER).pipe(
      take(1),
      catchError((err: HttpErrorResponse) => {
        this.handleLoginError(err);
        return throwError(err);
      })
    );
  } //login()

  private handleLoginError(exception: HttpErrorResponse) {
    if (exception && exception.status) {
      if (exception.status >= 500) {
        let message = exception.error.error;
        this.matSnackBar.open(message, null, { panelClass: 'snack-bar-error' });
      } else {
        let message = exception.error.error;
        this.matSnackBar.open(message, 'Close');
      }
    } // exception and status is available
  } // handleLoginError()
}
