import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditEventDialogComponent } from '../edit-event-dialog/edit-event-dialog.component';
import { EventsService } from '../service/events.service';


@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css']
})

export class EventEditingComponent implements OnInit {
  constructor(public dialog: MatDialog, private eventService: EventsService) {}
  ngOnInit(): void {
    this.eventService.getEvents('10ad2eb7-3dc4-4db3-bc0b-86bb3a1059d6').then((events: any) => {
      this.events = events;
    }).catch((error: any) => {
      console.error(error);
    });
  }

  events: string[] = [];

  openAddDialog() {
    const dialogRef = this.dialog.open(EditEventDialogComponent, {
      width: '270px',
      data: { name: '', location: '', description: '', serial: '' }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }


}
