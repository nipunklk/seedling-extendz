import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/shared/api/product/product';

@Directive({
  selector: '[appProduct]',
})
export class ProductDirective {
  @Input() product: Product;

  @HostListener('click', ['$event'])
  public handleClick(event: Event): void {
    console.log(event.target);
    
    if (event.target instanceof HTMLAnchorElement) {
      const element = event.target as HTMLAnchorElement;
      event.preventDefault();
      const route = element.getAttribute('href');
      if (route) this.router.navigate([route]);
    }
  }

  constructor(private el: ElementRef, private router: Router, private renderer: Renderer2) {}

  ngAfterViewInit() {
    let url: string = `/shop/brands/${this.product.url}`;
    this.renderer.setAttribute(this.el.nativeElement, 'href', url);
  }
}
