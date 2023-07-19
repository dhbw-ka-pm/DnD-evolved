import { Component, OnInit, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { DnDMap } from '../interfaces/DnDMap';
import { MapService } from '../service/maps.service';
import { MatDialog } from "@angular/material/dialog";
import { EditMapDialogComponent } from "../edit-map-dialog/edit-map-dialog.component";

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
  ) {
  }
  ngOnInit(): void {
    // this.DnDMaps=this.mockData
    this.mapService.getMaps().then(
      (maps: DnDMap[]) => {
        for (let i = 0; i < maps.length; i++) {
          this.DnDMaps.push(maps[i]);
        }
        console.log(this.DnDMaps);
      }
    )

    console.log(this.DnDMaps)
  }

  openEditDialog(map: DnDMap): void {
    const dialogRef = this.dialog.open(EditMapDialogComponent, {
      width: '260px',
      data: { ...map }
    });

    dialogRef.componentInstance.saveChanges.subscribe(updatedData => {
      console.log("Data to be saved: " + JSON.stringify(updatedData));
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // This is where the data gets returned (result) after clicking save button
    });
  }


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );





  mockData: DnDMap[] = [
    {
      Description: "A mysterious castle in the woods",
      Events: [],
      ImagePath: "castle.jpg",
      Name: "Castle of Shadows",
      Serial: "map_001",
      SizeX: 1000,
      SizeY: 800,
    },
    {
      Description: "An ancient temple hidden in the mountains",
      Events: [],
      ImagePath: "temple.jpg",
      Name: "Temple of the Ancients",
      Serial: "map_002",
      SizeX: 1200,
      SizeY: 900,
    },
    {
      Description: "A bustling city with winding streets",
      Events: [],
      ImagePath: "city.jpg",
      Name: "City of Thieves",
      Serial: "map_003",
      SizeX: 1800,
      SizeY: 1200,
    },
    {
      Description: "A treacherous swamp filled with dangerous creatures",
      Events: [],
      ImagePath: "swamp.jpg",
      Name: "Swamp of Sorrow",
      Serial: "map_004",
      SizeX: 800,
      SizeY: 1200,
    },
    {
      Description: "A vast ocean with uncharted islands",
      Events: [],
      ImagePath: "ocean.jpg",
      Name: "Sea of Mystery",
      Serial: "map_005",
      SizeX: 2000,
      SizeY: 2000,
    },
  ];

  // You can use the mockData array in your code to simulate real data.

}
