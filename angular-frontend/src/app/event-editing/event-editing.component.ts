import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditEventDialogComponent } from '../edit-event-dialog/edit-event-dialog.component';
import { EventsService } from '../service/events.service';
import {DnDEvent} from "../interfaces/Event";


@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css']
})

export class EventEditingComponent implements OnInit {
  constructor(public dialog: MatDialog, private eventService: EventsService) {}
  ngOnInit(): void {
    this.eventService.getEvents('10ad2eb7-3dc4-4db3-bc0b-86bb3a1059d6').then((events: any) => {
      this.eventSerials = events;
      for (let i = 0; i < this.eventSerials.length; i++) {
        console.log("for loop")
        this.eventService.getEventData(this.eventSerials[i]).then((event: DnDEvent) => {
          this.events.push(event);
          console.log(event);
        }).catch((error: any) => {
          console.error(error);
        });
      }
      console.log(events);
    }).catch((error: any) => {
      console.error(error);
    });

  }

  eventSerials: string[] = [];
  events: DnDEvent[] = [];


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


}
