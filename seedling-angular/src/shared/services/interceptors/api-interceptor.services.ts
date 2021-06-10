/***
 * @author Randika Hapugoda
 */
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { delay } from 'rxjs/operators';

export const API_TOKEN = 'API';

@Injectable()
export class ApiInterceptorService implements HttpInterceptor {
  constructor(@Inject(API_TOKEN) private baseUrl: string) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.startsWith('assets')) return next.handle(req);

    // Skip if base URL is there
    if (req.url.startsWith(this.baseUrl)) return next.handle(req);
    let headers = req.headers;
    headers = headers.append('Accept', 'application/hal+json');

    const apiReq = req.clone({
      url: `${this.baseUrl}${req.url}`,
      headers,
    });

    return next.handle(apiReq).pipe(delay(500));
  } // intercept()
} // class
