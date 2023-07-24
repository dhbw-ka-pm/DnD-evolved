import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {EditEventDialogComponent} from "../edit-event-dialog/edit-event-dialog.component";
import {EventsService} from '../service/events.service';
import {DnDEvent} from '../interfaces/DnDEvent';
import {ActivatedRoute, Router} from '@angular/router';
import {MapService} from '../service/maps.service';
import {DnDLocation} from '../interfaces/DnDLocation';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ShareCoordinatesService} from '../service/share-coordinates.service';
import {DeleteDialogComponent} from "../delete-dialog/delete-dialog.component";

// import { Location } from '@angular/common';

@Component({
  selector: 'app-event-editing',
  templateUrl: './event-editing.component.html',
  styleUrls: ['./event-editing.component.css'],
})
export class EventEditingComponent implements OnInit {
  constructor(
    public dialog: MatDialog,
    private deleteDialog: MatDialog,
    private eventService: EventsService,
    private route: ActivatedRoute,
    private mapService: MapService,
    private router: Router,
    private _snackBar: MatSnackBar,
    private shareCoordinates: ShareCoordinatesService,
    private changeDetectorRef: ChangeDetectorRef
  ) // private location: Location,
  {}

  alert(serial: string | undefined, event: MouseEvent) {
    this._snackBar.open('Please Click on a location on the map', '', {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000,
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
      duration: 3000,
    });
    event.stopPropagation();
    this.waitForClick().then(() => {
      this.openAddDialog();
    });
  }
  waitForClick(): Promise<void> {
    return new Promise(resolve => {
      const clickHandler = () => {
        document.removeEventListener('click', clickHandler);
        resolve();
      };
      document.addEventListener('click', clickHandler);
    });
  }
  ngOnInit(): void {
    this.mapSerial = this.route.snapshot.queryParamMap.get('id');
    console.log('Serial:');
    console.log(this.mapSerial);
    this.mapName = this.route.snapshot.queryParamMap.get('name');
    this.updateEvents();
  }

  updateEvents() {
    this.eventService
      .getEvents(this.mapSerial)
      .then((events: any) => {
        this.eventSerials = events;
        console.log('Event Serials: ' + this.eventSerials);
        this.events = [];
        const fetchEventPromises: Promise<any>[] = [];

        for (let i = 0; i < this.eventSerials.length; i++) {
          const fetchEventPromise = this.eventService
            .getEventData(this.eventSerials[i])
            .then((event: DnDEvent) => {
              return this.eventService
                .getEventLocation(this.mapSerial, this.eventSerials[i])
                .then((location: DnDLocation) => {
                  event.location = location;
                  return event;
                });
            });

          fetchEventPromises.push(fetchEventPromise);
        }

        // Wait for all the event fetch promises to resolve
        Promise.all(fetchEventPromises)
          .then((sortedEvents: DnDEvent[]) => {
            this.events = sortedEvents; // Populate the events array after all the fetch operations
            this.events.sort((a, b) => a.name[0].localeCompare(b.name[0])); // Sort events by name
            this.changeDetectorRef.detectChanges();
          })
          .catch((error: any) => {
            console.error(error);
          });
      })
      .catch((error: any) => {
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
        mapSerial: this.mapSerial,
      },
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

  openEditDialog(serial: string | undefined): void {
    let Event: DnDEvent = {
      name: '',
      description: '',
      serial: '',
      leadsToMapSerial: '',
      location: { x: 0, y: 0 },
    };
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
        newLocationY: this.shareCoordinates.getLocation()[1],
      },
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
    let Event: DnDEvent = {
      name: '',
      description: '',
      serial: '',
      leadsToMapSerial: '',
      location: { x: 0, y: 0 },
    };
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
      },
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

  openDeleteDialog(serial: string): void {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '250px',
      data: {serial: serial}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // You can implement deletion logic here
      if(result) {
        this.eventService.deleteEvent(serial, this.mapSerial)
        const currentUrl = this.router.url; // Get the current URL
        this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
          // Navigating to the same URL with skipLocationChange set to true triggers component reload
          this.router.navigateByUrl(currentUrl);
        });
        console.log('Confirmed deletion');
      }
    });
  }
}
