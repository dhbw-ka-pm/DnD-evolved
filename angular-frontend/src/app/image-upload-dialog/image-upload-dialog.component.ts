import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-image-upload-dialog',
  templateUrl: './image-upload-dialog.component.html',
  styleUrls: ['./image-upload-dialog.component.css']
})
export class ImageUploadDialogComponent {
  selectedFile: File | null = null;

  constructor(
    public dialogRef: MatDialogRef<ImageUploadDialogComponent>,
    private http: HttpClient,
    @Inject(MAT_DIALOG_DATA) public data: {serial: string}
  ) {
  }

  handleFileInput(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  upload(): void {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('image', this.selectedFile, this.selectedFile.name);
      this.http.post('http://193.196.37.17:8080/DnDEvolved/v1/images/' + this.data.serial, this.selectedFile, {headers: new HttpHeaders().set('Content-Type', 'image/jpeg')}).subscribe(
        (response) => {
          // Handle success response
          console.log('Upload successful', response);
          this.dialogRef.close(true);
        },
        (error) => {
          // Handle error response
          console.error('Upload failed', error);
          this.dialogRef.close(false);
        }
      );
    }
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
}
