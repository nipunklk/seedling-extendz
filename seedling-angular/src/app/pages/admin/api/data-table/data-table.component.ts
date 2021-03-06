import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EntityMeta, ExtApiConfig, EXT_API_CONFIG, getId } from 'extendz/core';
import { ObjectWithLinks } from 'extendz/core/models/hateos/object-with-links';
import { EntityMetaService } from 'extendz/service';
import { Subscription } from 'rxjs';
import { map, mergeMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss'],
})
export class DataTableComponent implements OnInit {
  public entityMeta: EntityMeta;
  private subscription: Subscription;

  constructor(
    private activatedRoute: ActivatedRoute,
    private entityMetaService: EntityMetaService,
    private router: Router,
    @Inject(EXT_API_CONFIG) public config: ExtApiConfig
  ) {}

  ngOnInit(): void {
    this.subscription = this.activatedRoute.params
      .pipe(
        map((p) => p.model),
        mergeMap((name) => this.entityMetaService.getModel(name)),
        tap((model) => (this.entityMeta = model))
      )
      .subscribe();
  } // ngOnInit()

  ngOnDestroy(): void {
    if (this.subscription) this.subscription.unsubscribe();
  }

  public onSelectEntity(entity: ObjectWithLinks): void {
    let id: string = 'new';
    if (entity) id = getId(entity._links.self.href);
    this.router.navigate([id], { relativeTo: this.activatedRoute });
  } // onSelectEntity()
} // class
