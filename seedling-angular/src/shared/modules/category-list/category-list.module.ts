import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router';
import { AppDireactivesModule } from 'src/shared/directives/direactives.module';
import { CategoryListComponent } from './category-list.component';

@NgModule({
  declarations: [CategoryListComponent],
  exports: [CategoryListComponent],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    AppDireactivesModule,
    MatButtonModule,
    MatListModule,
    MatMenuModule,
  ],
})
export class CategoryListModule {}
