import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {HttpClient, HttpHeaders} from "@angular/common/http";

export interface DialogData {
  serial: string;
  name: string;
  location: string;
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
      this.http.post('http://localhost:8080/DnDEvolved/v1/events/1078cf56-8ec0-4af7-88c6-0775c9f8307c', body, { headers: contentType })
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
    } else {
      this.http.patch('http://localhost:8080/DnDEvolved/v1/events/edit/e8207402-147e-4bfe-baa3-97575caa1e50', body, { headers: contentType })
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
    }


    this.dialogRef.close();
  }
}

