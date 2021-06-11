import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ExtRootModule } from 'extendz/api';
import { EXT_ROOT_CONFIG } from '../api.cofig';
import { RootRoutingModule } from './root-routing.module';
import { RootComponent } from './root.component';

@NgModule({
  declarations: [RootComponent],
  imports: [
    CommonModule,
    RootRoutingModule,
    ExtRootModule.forFeature(EXT_ROOT_CONFIG),
    MatToolbarModule,
    MatButtonModule,
  ],
})
export class RootModule {}
