import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { Brand } from 'src/shared/api/brand/brand';

@Directive({
  selector: '[appBrand]',
})
export class BrandDirective {
  @Input() brand: Brand;

  @HostListener('click', ['$event'])
  public handleClick(event: Event): void {
    if (event.target instanceof HTMLAnchorElement) {
      const element = event.target as HTMLAnchorElement;
      event.preventDefault();
      const route = element.getAttribute('href');
      if (route) this.router.navigate([route]);
    }
  }

  constructor(private el: ElementRef, private router: Router, private renderer: Renderer2) {}

  ngAfterViewInit() {
    let url: string = `/brands/${this.brand.url}`;
    this.renderer.setAttribute(this.el.nativeElement, 'href', url);
  }
}
