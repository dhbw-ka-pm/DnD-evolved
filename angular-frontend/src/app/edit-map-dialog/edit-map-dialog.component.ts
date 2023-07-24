import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {DnDMap} from '../interfaces/DnDMap';
import {ImageUploadDialogComponent} from "../image-upload-dialog/image-upload-dialog.component";
import {Router} from "@angular/router";

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
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: DnDMap) {
  }

  onSave() {
    this.saveChanges.emit(this.data);
    this.saveData();
    const currentUrl = this.router.url; // Get the current URL
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      // Navigating to the same URL with skipLocationChange set to true triggers component reload
      this.router.navigateByUrl(currentUrl);
    });
  }

  openUploadDialog(): void {
    const dialogRef = this.dialog.open(ImageUploadDialogComponent, {
      width: '400px',
      data: {serial: this.data.Serial}
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
      this.http.post('http://localhost:8080/DnDEvolved/v1/maps', body, {headers: contentType, responseType: "text"})
        .subscribe(
          response => {
            this.data.Serial = response;
            this.openUploadDialog()
            console.log(response)
            }, // Handle success here
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
