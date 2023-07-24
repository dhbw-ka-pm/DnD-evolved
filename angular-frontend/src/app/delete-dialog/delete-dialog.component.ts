import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
    template:`
    <h1 mat-dialog-title>Are you sure?</h1>
    <div mat-dialog-actions>
      <button mat-button (click)="onNoClick()">No</button>
      <button mat-button cdkFocusInitial (click)="onYesClick()">Yes</button>
    </div>
  `,
  selector: 'app-delete-dialog',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  onYesClick(): void {
    this.dialogRef.close(true);
  }
}


