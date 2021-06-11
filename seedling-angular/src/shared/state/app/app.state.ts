import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Computed, DataAction, Persistence, StateRepository } from '@ngxs-labs/data/decorators';
import { NgxsDataRepository } from '@ngxs-labs/data/repositories';
import { State } from '@ngxs/store';
import { throwError } from 'rxjs';
import { catchError, debounceTime, finalize, map, tap } from 'rxjs/operators';
import { UserLoginDto } from 'src/shared/api/user/user';
import { UserRoles } from 'src/shared/api/user/user-roles';
import { AccessTokenResponse } from 'src/shared/utils/access-token-response';
import { UserHttpService } from '../../api/user/user.http.service';
import { decodeJwt } from '../../utils/jwt-utils';

export class AppStateModel {
  darkMode: boolean;
  loading: boolean;
  tenant?: string;
  accessTokenResponse?: AccessTokenResponse;
  roles?: string[];
  email?: string;
}

@Persistence()
@StateRepository()
@Injectable()
@State<AppStateModel>({
  name: 'app',
  defaults: { darkMode: false, loading: false, tenant: 'seedling' },
})
export class AppState extends NgxsDataRepository<AppStateModel> {
  constructor(
    private userService: UserHttpService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    super();
  }

  @Computed() get loading$() {
    return this.state$.pipe(
      map((s) => s.loading),
      debounceTime(100)
    );
  }

  @Computed() get token() {
    return this.snapshot.accessTokenResponse?.access_token;
  }

  @Computed() get email() {
    return this.snapshot.email;
  }

  @Computed() get tenant() {
    return this.snapshot.tenant;
  }

  @DataAction() darkMode(darkMode: boolean) {
    this.patchState({ darkMode });
  }

  @DataAction({ insideZone: false }) loading(loading: boolean) {
    return this.patchState({ loading });
  }

  @DataAction() setTenant(tenant: string) {
    if (tenant) this.patchState({ tenant });
  }

  @DataAction() logout() {
    this.reset();
    this.router.navigate(['/']);
  }

  private navigate() {
    const roles = this.getState().roles;
    if (roles.includes(UserRoles.ROLE_ADMIN)) {
      this.router.navigate(['admin']);
    }
  }

  @DataAction() login(dto: UserLoginDto) {
    this.loading(true);
    return this.userService.login(dto).pipe(
      catchError((e: HttpErrorResponse) => this.handleError(e)),
      tap((r) => this.saveTokenAndRoles(r)),
      tap((_) => this.navigate()),
      finalize(() => this.loading(false))
    );
  }
  private handleError(e: HttpErrorResponse) {
    if (e.status == 426) this.router.navigate(['update'], { relativeTo: this.activatedRoute });
    return throwError(e);
  }

  private saveTokenAndRoles(accessTokenResponse: AccessTokenResponse) {
    const tk = decodeJwt(accessTokenResponse.access_token);
    const roles: string[] = tk.roles;
    const email = accessTokenResponse.username;
    this.patchState({ roles, accessTokenResponse, email });
  }
}
