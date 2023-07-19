import {Component, Input, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {EditEventDialogComponent} from "../edit-event-dialog/edit-event-dialog.component";
import {DnDEvent} from "../interfaces/Event";
import {EventsService} from "../service/events.service";

@Component({
  selector: 'app-event-entry',
  templateUrl: './event-entry.component.html',
  styleUrls: ['./event-entry.component.css']
})
export class EventEntryComponent implements OnInit{

  @Input() DnDEvent!: DnDEvent;
  constructor(public dialog: MatDialog, private eventService: EventsService) {}

  openEditDialog(): void {
    const dialogRef = this.dialog.open(EditEventDialogComponent, {
      width: '260px',
      data: {name: 'this', location: '12, 45', description: 'You will die here', serial: 'e8207402-147e-4bfe-baa3-97575caa1e50'}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }

  ngOnInit(): void {
  }

}
