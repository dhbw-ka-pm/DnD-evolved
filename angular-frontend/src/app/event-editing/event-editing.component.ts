import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditEventDialogComponent } from '../edit-event-dialog/edit-event-dialog.component';
import { EventsService } from '../service/events.service';
import { DnDEvent } from "../interfaces/DnDEvent";
import { ActivatedRoute, Router } from '@angular/router';
import { MapService } from '../service/maps.service';
import { DnDLocation } from "../interfaces/DnDLocation";
import { MatSnackBar } from '@angular/material/snack-bar';
import { ShareCoordinatesService } from '../service/share-coordinates.service';

// import { Location } from '@angular/common';


@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css']
})

export class EventEditingComponent implements OnInit {
  constructor(
    public dialog: MatDialog,
    private eventService: EventsService,
    private route: ActivatedRoute,
    private mapService: MapService,
    private router: Router,
    private _snackBar: MatSnackBar,
    private shareCoordinates: ShareCoordinatesService,
    // private location: Location,
  ) {}

  alert(serial: string | undefined, event: MouseEvent) {
    this._snackBar.open('Please Click on a location on the map', '', {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000

    });
    event.stopPropagation();
    this.waitForClick().then(() => {
      this.openChangeLocationDialog(serial);
    });
  }

  addEvent(event: MouseEvent) {
    this._snackBar.open('Please Click on a location on the map', '', {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000

    });
    event.stopPropagation();
    this.waitForClick().then(() => {
      this.openAddDialog();
    });
  }
  waitForClick(): Promise<void> {
    return new Promise((resolve) => {
      const clickHandler = () => {
        document.removeEventListener('click', clickHandler);
        resolve();
      };
      document.addEventListener('click', clickHandler);
    });
  }
  ngOnInit(): void {
    this.mapSerial = this.route.snapshot.queryParamMap.get('id');
    console.log("Serial:");
    console.log(this.mapSerial);
    this.mapName = this.route.snapshot.queryParamMap.get('name');
    this.updateEvents();
  }

  updateEvents() {
    this.eventService.getEvents(this.mapSerial).then((events: any) => {
      this.eventSerials = events;
      this.events = [];
      for (let i = 0; i < this.eventSerials.length; i++) {
        this.eventService.getEventData(this.eventSerials[i]).then((event: DnDEvent) => {
          this.eventService.getEventLocation(this.mapSerial, this.eventSerials[i]).then((location: DnDLocation) => {
            event.location = location
            console.log(event.location)
          })
          this.events.push(event);
        }).catch((error: any) => {
          console.error(error);
        });
      }
      console.log(events);
    }).catch((error: any) => {
      console.error(error);
    });
  }

  eventSerials: string[] = [];
  events: DnDEvent[] = [];
  mapSerial: any = '';
  mapName: any = '';


  openAddDialog() {
    const dialogRef = this.dialog.open(EditEventDialogComponent, {
      width: '270px',
      data: {
        name: '',
        locationX: this.shareCoordinates.getLocation()[0],
        locationY: this.shareCoordinates.getLocation()[1],
        description: '',
        serial: '',
        mapSerial: this.mapSerial
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.updateEvents();
      const currentUrl = this.router.url; // Get the current URL
      this.router.navigateByUrl('/',
        { skipLocationChange: true }).then(() => {
          // Navigating to the same URL with skipLocationChange set to true triggers component reload
          this.router.navigateByUrl(currentUrl);
        });
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }

  openEditDialog(serial: string | undefined): void {
    let Event: DnDEvent = { name: '', description: '', serial: '' };
    for (let i = 0; i < this.events.length; i++) {
      if (this.events[i].serial === serial) {
        Event = this.events[i];
      }
    }

    const dialogRef = this.dialog.open(EditEventDialogComponent, {
      width: '260px',
      data: {
        name: Event.name,
        locationX: Event.location?.x,
        locationY: Event.location?.y,
        description: Event.description,
        serial: Event.serial,
        mapSerial: this.mapSerial,
        newLocationX: this.shareCoordinates.getLocation()[0],
        newLocationY: this.shareCoordinates.getLocation()[1]
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.updateEvents();
      const currentUrl = this.router.url; // Get the current URL
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        // Navigating to the same URL with skipLocationChange set to true triggers component reload
        this.router.navigateByUrl(currentUrl);
      });
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }

  openChangeLocationDialog(serial: string | undefined): void {
    let Event: DnDEvent = { name: '', description: '', serial: '' };
    for (let i = 0; i < this.events.length; i++) {
      if (this.events[i].serial === serial) {
        Event = this.events[i];
      }
    }

    const dialogRef = this.dialog.open(EditEventDialogComponent, {
      width: '260px',
      data: {
        name: Event.name,
        description: Event.description,
        locationX: this.shareCoordinates.getLocation()[0],
        locationY: this.shareCoordinates.getLocation()[1],
        serial: Event.serial,
        mapSerial: this.mapSerial,
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.updateEvents();
      const currentUrl = this.router.url; // Get the current URL
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        // Navigating to the same URL with skipLocationChange set to true triggers component reload
        this.router.navigateByUrl(currentUrl);
      });
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }

}
