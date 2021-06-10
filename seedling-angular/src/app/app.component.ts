import { AfterViewInit, Component, Inject, InjectionToken } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { AppState } from 'src/shared/state/app/app.state';

export const LOCATION_TOKEN = new InjectionToken<Location>('Window location object');

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [{ provide: LOCATION_TOKEN, useValue: window.location }],
})
export class AppComponent implements AfterViewInit {
  public isDark: boolean = false;

  constructor(
    public appState: AppState,
    private iconRegistry: MatIconRegistry,
    @Inject(LOCATION_TOKEN) private location: Location,
    private sanitizer: DomSanitizer
  ) {
    console.log('App component');

    let svgUrl = 'assets/svg/app.svg';
    const x = this.sanitizer.bypassSecurityTrustResourceUrl(svgUrl);
    this.iconRegistry.addSvgIconSetInNamespace('app', x);

    const domain = this.location.hostname;
    let subdomain: string;
    if (domain.indexOf('.') < 0 || domain.split('.')[0] === 'www') subdomain = null;
    else subdomain = domain.split('.')[0];

    console.log("Setting subDoman ",subdomain);
    
    this.appState.setTenant(subdomain);
  }

  ngAfterViewInit(): void {
    this.appState.loading(false);
  }
}
