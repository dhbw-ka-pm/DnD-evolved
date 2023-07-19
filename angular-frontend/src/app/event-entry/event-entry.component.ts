import {Component, Input} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {EditEventDialogComponent} from "../edit-event-dialog/edit-event-dialog.component";

export interface EventData {
  serial: string;
  name: string;
  location: string;
  description: string;
}
@Component({
  selector: 'app-event-entry',
  templateUrl: './event-entry.component.html',
  styleUrls: ['./event-entry.component.css']
})
export class EventEntryComponent {

  @Input() data!: string;
  constructor(public dialog: MatDialog) {}

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
