import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HateosPagedResponse, ObjectWithLinks } from 'extendz/core';
import { Observable, throwError } from 'rxjs';
import { catchError, take, tap } from 'rxjs/operators';

export const PUBLIC_HEADER = {
  headers: { 'X-Tenant': 'public' },
};

export class BaseHttpService {
  constructor(
    protected http: HttpClient,
    protected snackBar: MatSnackBar,
    protected baseUrl: string
  ) {}

  public save<T>(object: ObjectWithLinks): Observable<T> {
    if (object._links) {
      return this.http.patch<T>(object._links.self!.href, object).pipe(
        take(1),
        tap((_) => {
          this.snackBar.open('Updated', null!, {
            panelClass: ['snack-bar-info'],
          });
        })
      );
    } else
      return this.http.post<T>(this.baseUrl, object).pipe(
        take(1),
        tap((_) => {
          this.snackBar.open('Saved', null, {
            panelClass: ['snack-bar-info'],
          });
        })
      );
  }

  public delete<T>(setting: ObjectWithLinks) {
    return this.http.delete(setting._links!.self!.href).pipe(
      take(1),
      tap((_) => this.showSuccess('Deleted')),
      catchError((e: HttpErrorResponse) => this.showErrorAndThow(e))
    );
  }

  public getAll(): Observable<HateosPagedResponse> {
    return this.http.get<HateosPagedResponse>(this.baseUrl).pipe(take(1));
  }

  public findOne<T>(id: string): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${id}`).pipe(take(1));
  }

  public uploadFile(entity: ObjectWithLinks, property: string, formData: FormData) {
    const url = `${entity._links!.self!.href}/${property}`;
    return this.http.post(url, formData).pipe(
      take(1),
      tap((_) => this.showInfo('Uploaded'))
    );
  }

  showInfo(message: string) {
    this.snackBar.open(message, null!, {
      panelClass: ['snack-bar-info'],
    });
  }

  showSuccess(message: string) {
    this.snackBar.open(message, null!, {
      panelClass: 'snack-bar-success',
    });
  }

  showErrorAndThow(response: HttpErrorResponse, message?: string) {
    if (!message) message = response.error.message;
    this.snackBar.open(message!, null!, {
      panelClass: 'snack-bar-error',
    });
    return throwError(response);
  } //showError()
}
