import { Component, OnInit, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { DnDMap } from '../interfaces/DnDMap';
import { MapService } from '../map.service';

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
    this.getDnDMaps()
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
}
