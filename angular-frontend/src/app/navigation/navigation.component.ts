import { Component, inject, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { DnDMap } from '../interfaces/DnDMap';
import { MapService } from '../service/maps.service';
import { MatDialog } from '@angular/material/dialog';
import { EditMapDialogComponent } from '../edit-map-dialog/edit-map-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
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
      // console.log("Data to be saved: " + JSON.stringify(updatedData));
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
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
