import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { EditMapDialogComponent } from '../edit-map-dialog/edit-map-dialog.component';
import { DnDMap } from '../interfaces/DnDMap';
import { MapService } from '../service/maps.service';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  deleteMap(serial: string) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '250px',
      data: { serial: serial }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // You can implement deletion logic here
      if (result) {
        this.mapService.deleteMap(serial)
        this.updateMaps();
        const currentUrl = this.router.url; // Get the current URL
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          // Navigating to the starting route (in our case this is EventEditingComponent)
          this.router.navigateByUrl('/');
        });
        console.log('Confirmed deletion');
      }
    });

  }
  private breakpointObserver = inject(BreakpointObserver);

  DnDMaps: DnDMap[] = [];

  constructor(
    private mapService: MapService,
    public dialog: MatDialog,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.updateMaps();
  }

  openEditDialog(add: boolean, map?: DnDMap): void {
    if (add) {
      map = {
        Description: '',
        ImagePath: '',
        Name: '',
        Serial: '',
      };
    }
    const dialogRef = this.dialog.open(EditMapDialogComponent, {
      width: '260px',
      data: { ...map },
    });

    dialogRef.componentInstance.saveChanges.subscribe(updatedData => {
      console.log("Data to be saved: " + JSON.stringify(updatedData));
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
      this.updateMaps();
      const currentUrl = this.router.url;
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        // Navigating to the same URL with skipLocationChange set to true triggers component reload
        this.router.navigateByUrl(currentUrl);
      });
    });
  }

  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  reload() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      // Navigating to the same URL with skipLocationChange set to true triggers component reload
      this.router.navigateByUrl(currentUrl);
    });
  }
  updateMaps() {
    this.mapService.getMaps().then((maps: DnDMap[]) => {
      this.DnDMaps = [];
      const fetchMapPromises: Promise<DnDMap>[] = [];
      for (let i = 0; i < maps.length; i++) {
        const fetchEventPromise = this.mapService
          .getMapBySerial(maps[i].Serial)
          .then((map: DnDMap) => {
            return map;
          });
        fetchMapPromises.push(fetchEventPromise);
      }
      // wait for all promises to resolve
      Promise.all(fetchMapPromises).then((sortedMaps: DnDMap[]) => {
        this.DnDMaps = sortedMaps;
        this.DnDMaps.sort((a, b) => a.Name[0].localeCompare(b.Name[0]));
      });
    });
    console.log(this.DnDMaps);
  }
}
