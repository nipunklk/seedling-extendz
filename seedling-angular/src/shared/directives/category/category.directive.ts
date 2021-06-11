import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/shared/api/category/category';

@Directive({
  selector: '[appCategory]',
})
export class CategoryDirective {
  @Input() category: Category;

  @HostListener('click', ['$event'])
  public handleClick(event: Event): void {
    event.preventDefault();

    const element = event.currentTarget as HTMLAnchorElement;
    event.preventDefault();
    const route = element.getAttribute('href');
    if (route) this.router.navigate([route]);
  }

  constructor(private el: ElementRef, private router: Router, private renderer: Renderer2) {}

  ngAfterViewInit() {
    let url: string = `/categories/${this.category.url}`;
    this.renderer.setAttribute(this.el.nativeElement, 'href', url);
  }
}
