import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

export interface DialogData {
  mapSerial: string;
  serial: string;
  name: string;
  locationX: string;
  locationY: string;
  description: string;
  newLocationX: string
  newLocationY: string
}

@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-event-dialog.component.html',
  styleUrls: ['./edit-event-dialog.component.css']
})
export class EditEventDialogComponent implements OnInit {

  eventForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<EditEventDialogComponent>,
    private http: HttpClient,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private formBuilder: FormBuilder,
  ) {
    this.eventForm = this.formBuilder.group({
      name: [data?.name || '', Validators.required],
      locationX: [data?.locationX || '', Validators.required],
      locationY: [data?.locationY || '', Validators.required],
      description: [data?.description || '', Validators.required]
    });
  }
  ngOnInit(): void {
    this.initializeForm();
  }
  initializeForm() {
    if (this.data) {
      this.eventForm = this.formBuilder.group({
        name: [this.data?.name || '', Validators.required],
        locationX: [this.data?.locationX || '', Validators.required],
        locationY: [this.data?.locationY || '', Validators.required],
        description: [this.data?.description || '', Validators.required]
      });
    }
  }

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


    if (this.data.serial === '') {
      let newSerial = '';
      this.http.post('http://localhost:8080/DnDEvolved/v1/events/' + this.data.mapSerial, body, {
        headers: contentType,
        responseType: "text"
      })
        .subscribe(
          response => {
            console.log(response);
            newSerial = response;
            this.http.patch('http://127.0.0.1:8080/DnDEvolved/v1/maps/' + this.data.mapSerial + '/events/' + newSerial + '/' + this.data.locationX + ',' + this.data.locationY, body, { headers: contentType })
              .subscribe(
                response => console.log(response), // Handle success here
                error => console.log(error) // Handle error here
              );
          }, // Handle success here
          error => console.log(error) // Handle error here
        );
    } else {
      this.http.patch('http://localhost:8080/DnDEvolved/v1/events/edit/' + this.data.serial, body, { headers: contentType })
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
      this.http.patch('http://127.0.0.1:8080/DnDEvolved/v1/maps/' + this.data.mapSerial + '/events/' + this.data.serial + '/' + this.data.locationX + ',' + this.data.locationY, body, { headers: contentType })
        .subscribe(
          response => console.log(response), // Handle success here
          error => console.log(error) // Handle error here
        );
    }
    this.dialogRef.close();
  }
}

