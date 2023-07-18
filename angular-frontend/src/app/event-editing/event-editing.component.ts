import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { EventsService } from '../service/events.service';


@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css']
})

export class EventEditingComponent implements OnInit {
  constructor(public dialog: MatDialog, private eventService: EventsService) {}
  ngOnInit(): void {
    this.events = this.eventService.getEvents();
    console.log("the next print statement is important ");
    console.log(this.events);
  }

  events: string[] = [];

  openAddDialog() {
    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '260px',
      data: { name: '', location: '', description: '', serial: '' }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }


}
