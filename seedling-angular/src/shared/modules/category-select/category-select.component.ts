import { SelectionModel } from '@angular/cdk/collections';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatListOption, MatSelectionListChange } from '@angular/material/list';

@Component({
  selector: 'app-category-select',
  templateUrl: './category-select.component.html',
  styleUrls: ['./category-select.component.scss'],
})
export class CategorySelectComponent {
  @Input() subHeader: string;
  @Input() items: any[];

  @Output() selectedChange = new EventEmitter<string[]>();

  selectedOptions: SelectionModel<MatListOption>;

  onSelection(event: MatSelectionListChange) {
    this.selectedChange.emit(event.source.selectedOptions.selected.map((s) => s.value));
  }
}
