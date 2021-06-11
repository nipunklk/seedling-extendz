import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { EntityMeta } from 'extendz/core';
import { Observable, of } from 'rxjs';
import { map, mergeMap, tap } from 'rxjs/operators';
import { AppState } from 'src/shared/state/app/app.state';
import { EntityService } from './entity.service';

export interface EntityComponentResolverData {
  entityMeta: EntityMeta;
  entity: any;
}

@Injectable({ providedIn: 'any' })
export class EntityComponentResolverService implements Resolve<EntityComponentResolverData> {
  constructor(private entityService: EntityService, private appstate: AppState) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<EntityComponentResolverData> {
    const model = route.params['model'];
    const id = route.params['id'];
    let entityMeta: EntityMeta;
    this.appstate.loading(true);

    return this.entityService.entityMetaService.getModel(model).pipe(
      tap((em) => (entityMeta = em)),
      mergeMap((m) => {
        if (id == 'new') return of(null);
        return this.entityService.getOne(m, id);
      }),
      map((entity) => ({
        entityMeta,
        entity,
      })),
      tap((_) => this.appstate.loading(false))
    );
  } //resolve
} // class
