import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material/snack-bar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ServiceWorkerModule } from '@angular/service-worker';
import { NgxsDataPluginModule } from '@ngxs-labs/data';
import { NGXS_DATA_STORAGE_PLUGIN } from '@ngxs-labs/data/storage';
import { NgxsFormPluginModule } from '@ngxs/form-plugin';
import { NgxsModule, NoopNgxsExecutionStrategy } from '@ngxs/store';
import { environment } from 'src/environments/environment';
import {
  ApiInterceptorService,
  API_TOKEN,
} from 'src/shared/services/interceptors/api-interceptor.services';
import { TokenExpiredInterceptor } from 'src/shared/services/interceptors/token-expired-interceptor';
import { TokenInterceptorService } from 'src/shared/services/interceptors/token-intercept.services';
import { AppState } from 'src/shared/state/app/app.state';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    // Ngxs
    NgxsModule.forRoot([AppState], {
      developmentMode: !environment.production,
      executionStrategy: NoopNgxsExecutionStrategy,
    }),
    NgxsDataPluginModule.forRoot([NGXS_DATA_STORAGE_PLUGIN]),
    NgxsFormPluginModule.forRoot(),
    MatSnackBarModule,
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
  ],
  providers: [
    { provide: API_TOKEN, useValue: environment.basePath },
    { provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: { duration: 2500 } },
    { provide: HTTP_INTERCEPTORS, useClass: ApiInterceptorService, multi: true },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true,
    },
    { provide: HTTP_INTERCEPTORS, useClass: TokenExpiredInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
