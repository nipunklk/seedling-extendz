/***
 * @author Randika Hapugoda
 */
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppState } from 'src/shared/state/app/app.state';

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {
  constructor(private appState: AppState) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Iggnore assets
    if (req.url.startsWith('assets')) return next.handle(req);

    let headers = req.headers;

    if (this.appState.token)
      headers = headers.append('Authorization', 'Bearer ' + this.appState.token);

    // if (headers.get('X-Tenant') == null && this.appState.tenant != null)
    //   headers = headers.append('X-Tenant', this.appState.tenant);

    const tokenizedRequest = req.clone({
      headers,
    });
    return next.handle(tokenizedRequest);
  } // intercept()
} // class
