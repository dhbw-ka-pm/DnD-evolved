import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import {HttpClient} from "@angular/common/http";
import * as xml2js from 'xml2js';


@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css']
})

export class EventEditingComponent {
  constructor(public dialog: MatDialog, private http: HttpClient) {}

  events: string[] = [];

  openAddDialog() {
    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '260px',
      data: {name: '', location: '', description: '', serial: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }

  onInit() {
    this.getEvents();
  }

  getEvents()  {
    this.http.get('http://localhost:8080/DnDEvolved/v1/maps/1078cf56-8ec0-4af7-88c6-0775c9f8307c', { responseType: 'text' }).subscribe((xmlData: string) => {
        const p: xml2js.Parser = new xml2js.Parser();
        p.parseString(xmlData, (err, result) => {
          if (err) {
            throw err;
          }
          const json =JSON.stringify(result, null, 4);
          console.log(json)
          this.events = result.Map.Event;
        });
      });

  }
}
