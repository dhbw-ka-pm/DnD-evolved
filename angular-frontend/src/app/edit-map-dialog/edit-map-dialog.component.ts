import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {DnDMap} from '../interfaces/DnDMap';
import {ImageUploadDialogComponent} from "../image-upload-dialog/image-upload-dialog.component";

@Component({
  selector: 'app-edit-map-dialog',
  templateUrl: './edit-map-dialog.component.html',
  styleUrls: ['./edit-map-dialog.component.css']
})
export class EditMapDialogComponent {
  mapApi = 'http://localhost:8080/DnDEvolved/v1/maps/'
  @Output() saveChanges: EventEmitter<DnDMap> = new EventEmitter<DnDMap>();

  constructor(
    public dialogRef: MatDialogRef<EditMapDialogComponent>,
    private http: HttpClient,
    private dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: DnDMap) {
  }

  onSave() {
    this.saveChanges.emit(this.data);
    this.saveData();
    this.openUploadDialog()
  }

  openUploadDialog(): void {
    const dialogRef = this.dialog.open(ImageUploadDialogComponent, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Image upload was successful
        // Perform any additional actions
      } else {
        // Image upload was canceled or failed
        // Perform any error handling or cleanup
      }
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveData(): void {
    const contentType = new HttpHeaders().set('Content-Type', 'application/xml');

    const body = `
 <Map>
    <Name>${this.data.Name}</Name>
    <Description>${this.data.Description}</Description>
    <SizeX>${this.data.SizeX}</SizeX>
    <SizeY>${this.data.SizeY}</SizeY>
  </Map>
    `;


    if (this.data.Serial === '') {
      this.http.post('http://localhost:8080/DnDEvolved/v1/maps', body, {headers: contentType})
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
