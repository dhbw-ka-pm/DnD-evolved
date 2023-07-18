import { Component, OnInit, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { DnDMap } from '../interfaces/DnDMap';
import { MapService } from '../service/maps.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  // mapService: MapService =inject(MapService)
  private breakpointObserver = inject(BreakpointObserver);

  DnDMaps: DnDMap[] = [];

  constructor(private mapService: MapService) {
  }
  ngOnInit(): void {
    // this.getDnDMaps()
    this.DnDMaps=this.mockData
    console.log(this.DnDMaps)
  }



  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  getDnDMaps(): void {
    this.mapService.getMaps()
      .subscribe(maps => this.DnDMaps = maps);
  }
  mockData: DnDMap[] = [
    {
      description: "A mysterious castle in the woods",
      events: [],
      imagePath: "castle.jpg",
      name: "Castle of Shadows",
      serial: "map_001",
      sizeX: 1000,
      sizeY: 800,
    },
    {
      description: "An ancient temple hidden in the mountains",
      events: [],
      imagePath: "temple.jpg",
      name: "Temple of the Ancients",
      serial: "map_002",
      sizeX: 1200,
      sizeY: 900,
    },
    {
      description: "A bustling city with winding streets",
      events: [],
      imagePath: "city.jpg",
      name: "City of Thieves",
      serial: "map_003",
      sizeX: 1800,
      sizeY: 1200,
    },
    {
      description: "A treacherous swamp filled with dangerous creatures",
      events: [],
      imagePath: "swamp.jpg",
      name: "Swamp of Sorrow",
      serial: "map_004",
      sizeX: 800,
      sizeY: 1200,
    },
    {
      description: "A vast ocean with uncharted islands",
      events: [],
      imagePath: "ocean.jpg",
      name: "Sea of Mystery",
      serial: "map_005",
      sizeX: 2000,
      sizeY: 2000,
    },
  ];

  // You can use the mockData array in your code to simulate real data.

}
