import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngxs/store';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class TokenExpiredInterceptor implements HttpInterceptor {
  constructor(private router: Router, private store: Store) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
    return next
      .handle(req)
      .pipe
      // catchError((e: HttpErrorResponse) => {
      //   console.log(e);

      //   if (e.status === 401)
      //     // if (!this.router.url.startsWith(LOGIN_NAV)) this.store.dispatch(new Logout());
      //     return throwError(e.error);
      // })
      ();
  } // intercept()
}
