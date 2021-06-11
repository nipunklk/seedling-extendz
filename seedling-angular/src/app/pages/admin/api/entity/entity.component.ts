import { Component, OnDestroy, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Action, ObjectWithLinks, Property } from 'extendz/core';
import { Subscription } from 'rxjs';
import { filter, mergeMap, take } from 'rxjs/operators';
import { ProjectSaveDto } from 'src/shared/api/project/project';
import { ProjectHttpService } from 'src/shared/api/project/project.http.service';
import { EntityComponentResolverData } from './entity-component-resolver.service';
import { EntityService } from './entity.service';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrls: ['./entity.component.scss'],
})
export class EntityComponent implements OnInit, OnDestroy {
  data: EntityComponentResolverData;

  @ViewChild('alertDialog') alertDialog: TemplateRef<any>;

  dataSubScription: Subscription;

  constructor(
    private activatedRoute: ActivatedRoute,
    private entityService: EntityService,
    private projectHttpService: ProjectHttpService,
    private dialog: MatDialog
  ) {
    this.dataSubScription = this.activatedRoute.params.subscribe(
      (d) => (this.data = this.activatedRoute.snapshot.data.extendz)
    );
  }

  ngOnDestroy(): void {
    if (this.dataSubScription) this.dataSubScription.unsubscribe();
  }

  ngOnInit(): void {}

  onAction(action: Action) {
    console.log(action);

    switch (action.id) {
      case 'createProject':
        const salesOrder = (action.entity as ObjectWithLinks)._links.self.href;
        let project: ProjectSaveDto = {
          salesOrder,
        };
        let ref = this.dialog.open(this.alertDialog, { width: '20vw' });
        ref
          .afterClosed()
          .pipe(
            take(1),
            filter((d: string) => d == 'true'),
            mergeMap((_) => this.projectHttpService.save(project).pipe(take(1)))
          )
          .subscribe((d) => {
            let property = new Property();
            property.reference = 'project';
            this.entityService.navigateExisting(property, d);
          });

        break;
      case 'viewProject':
        let property = new Property();
        property.reference = 'project';
        this.entityService.navigateExisting(property, action.entity.project);
        break;
      case 'viewSalesOrder':
        let p = new Property();
        p.reference = 'salesOrder';
        this.entityService.navigateExisting(p, action.entity.salesOrder);
        break;
    }
  }
}
