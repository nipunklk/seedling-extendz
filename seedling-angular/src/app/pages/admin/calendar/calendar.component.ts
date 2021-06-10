import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss'],
})
export class CalendarComponent implements OnInit {
  view: CalendarView = CalendarView.Month;

  viewDate = new Date();

  events: CalendarEvent[] = [
    {
      title: 'An event',
      start: new Date(),
    },
  ];

  private readonly darkThemeClass = 'dark-theme';

  constructor(@Inject(DOCUMENT) private document) {}

  ngOnInit(): void {
    // this.document.body.classList.add(this.darkThemeClass);
  }

  ngOnDestroy(): void {
    // this.document.body.classList.remove(this.darkThemeClass);
  }
}
