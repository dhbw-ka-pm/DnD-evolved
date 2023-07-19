import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { DnDMap } from '../interfaces/DnDMap';

@Component({
  selector: 'app-edit-map-dialog',
  templateUrl: './edit-map-dialog.component.html',
  styleUrls: ['./edit-map-dialog.component.css']
})
export class EditMapDialogComponent {
  mapApi = 'http://localhost:8080/DnDEvolved/v1/maps/'
  @Output() saveChanges: EventEmitter<DnDMap> = new EventEmitter<DnDMap>();

  onSave() {
    this.saveChanges.emit(this.data);
    this.saveData();
  }


  constructor(
    public dialogRef: MatDialogRef<EditMapDialogComponent>,
    private http: HttpClient,
    @Inject(MAT_DIALOG_DATA) public data: DnDMap) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveData(): void {
    const contentType = new HttpHeaders().set('Content-Type', 'application/xml');

    const body = `
 <Map>
    <name>${this.data.Name}</name>
    <description>${this.data.Description}</description>
    <sizeX>${this.data.SizeX}</sizeX>
    <sizeY>${this.data.SizeY}</sizeY>
  </Map>
    `;


    if (this.data.Serial === '') {
      this.http.post(this.mapApi + this.data.Serial, body, { headers: contentType })
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
    } else {
      this.http.patch(this.mapApi + this.data.Serial, body, { headers: contentType })
        .subscribe(
          response => {
            console.log(response);
            console.log("body: " + body)
          }, // Handle success here

          error => console.log(error) // Handle error here
        );
    }


    this.dialogRef.close();
  }
}
