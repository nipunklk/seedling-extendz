import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Computed, DataAction, Persistence, StateRepository } from '@ngxs-labs/data/decorators';
import { NgxsDataRepository } from '@ngxs-labs/data/repositories';
import { State } from '@ngxs/store';
import { EntityMeta } from 'extendz/core';
import { map } from 'rxjs/operators';

export class AdminStateModel {
  sidenavOpened: boolean;
  rightSidenavOpened: boolean;
}

@Injectable()
@Persistence()
@StateRepository()
@State<AdminStateModel>({
  name: 'admin',
  defaults: { sidenavOpened: true, rightSidenavOpened: true },
})
export class AdminState extends NgxsDataRepository<AdminStateModel> {
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    super();
  }

  @Computed() get sideNavOpened$() {
    return this.state$.pipe(map((s) => s.sidenavOpened));
  }

  @Computed() get rightSidenavOpened$() {
    return this.state$.pipe(map((s) => false));
  }

  @DataAction() toggleSidenav(): void {
    this.patchState({ sidenavOpened: !this.getState().sidenavOpened });
  }

  @DataAction() toggleRightSidenav(): void {
    console.log(!this.getState().rightSidenavOpened);

    this.patchState({
      rightSidenavOpened: !this.getState().rightSidenavOpened,
    });
  }

  @DataAction()
  public onModel(enityMeta: EntityMeta) {
    this.router.navigate([`/admin/api/${enityMeta.name}`], { relativeTo: this.activatedRoute });
  }

  @DataAction({ insideZone: true })
  public logout(): void {
    this.reset();
    this.router.navigate(['']);
  }
}
