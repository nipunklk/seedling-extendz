import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Batch } from 'src/shared/api/batch/batch';
import { BatchHttpSerivce, BATCH_URL } from 'src/shared/api/batch/batch.http.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  constructor(private batchService: BatchHttpSerivce) {}

  batch$: Observable<Batch[]>;


  ngOnInit(): void {
    this.batch$ = this.batchService.getAll().pipe(map((d) => d._embedded[BATCH_URL]));
  }
}
