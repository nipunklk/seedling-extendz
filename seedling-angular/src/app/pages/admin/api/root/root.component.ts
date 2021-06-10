import { Component } from '@angular/core';
import { AdminState } from 'src/shared/state/admin.state';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss'],
})
export class RootComponent {
  constructor(public state: AdminState) {}
} //class
