import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ExtRootModule } from 'extendz/api';
import { ApiRoutingModule } from './api-routing.module';
import { ApiComponent } from './api.component';

@NgModule({
  declarations: [ApiComponent],
  imports: [
    CommonModule,
    ApiRoutingModule,
    //
    ExtRootModule,
  ],
})
export class AdminApiModule {}
