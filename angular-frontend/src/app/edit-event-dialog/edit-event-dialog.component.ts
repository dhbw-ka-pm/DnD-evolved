import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {HttpClient, HttpHeaders} from "@angular/common/http";

export interface DialogData {
  mapSerial: string;
  serial: string;
  name: string;
  locationX: string;
  locationY: string;
  description: string;
}

@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-event-dialog.component.html',
  styleUrls: ['./edit-event-dialog.component.css']
})
export class EditEventDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<EditEventDialogComponent>,
    private http: HttpClient,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveData(): void {
    const contentType = new HttpHeaders().set('Content-Type', 'application/xml');

    const body = `
    <Event>
    <description>${this.data.description}</description>
    <leadsToMapSerial></leadsToMapSerial>
    <name>${this.data.name}</name>
    <serial>${this.data.serial}</serial>
    </Event>`;


    if(this.data.serial === '') {
      this.http.post('http://localhost:8080/DnDEvolved/v1/events/' + this.data.mapSerial, body, {headers: contentType})
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
    } else {
      this.http.patch('http://localhost:8080/DnDEvolved/v1/events/edit/' + this.data.serial, body, {headers: contentType})
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
      this.http.patch('http://127.0.0.1:8080/DnDEvolved/v1/maps/' + this.data.mapSerial + '/events/' + this.data.serial + '/' + this.data.locationX + ',' + this.data.locationY, body, {headers: contentType})
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
    }
    this.dialogRef.close();
  }
}

