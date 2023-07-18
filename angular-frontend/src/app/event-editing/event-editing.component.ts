import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';


@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css']
})

export class EventEditingComponent {
  constructor(public dialog: MatDialog) {}
  openDialog(): void {
    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '250px',
      data: {name: 'Deadman\'s Cave', location: '12, 45', description: 'You will die here'}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }


}
