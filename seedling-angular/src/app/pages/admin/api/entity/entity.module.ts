import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { ExtEntityModule } from 'extendz/api';
import { DATA_TABEL_CONFIG, DATA_TABLE_SERVICE, ENTITY_CONFIG, ENTITY_SERVICE } from '../api.cofig';
import { EntityRoutingModule } from './entity-routing.module';
import { EntityComponent } from './entity.component';

@NgModule({
  declarations: [EntityComponent],
  providers: [ENTITY_SERVICE, DATA_TABLE_SERVICE, DATA_TABEL_CONFIG],
  imports: [
    CommonModule,
    EntityRoutingModule,
    ExtEntityModule.forFeature(ENTITY_CONFIG),
    MatDialogModule,
    MatButtonModule,
  ],
})
export class EntityModule {}
