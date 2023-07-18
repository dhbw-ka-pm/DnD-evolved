import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


import { DnDMap } from './interfaces/DnDMap';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private apiUrl = 'http://localhost:8080/DnDEvolved/v1/maps/'
  constructor(private http: HttpClient) {}

  // Create a method to fetch maps from the API
  getMaps(): Observable<DnDMap[]> {
    return this.http.get<DnDMap[]>(this.apiUrl)
    //   .pipe(
    //   map((response: any[]) => {
    //     return response.map(mapData => this.mapApiResponseToMapObject(mapData));
    //   })
    // );
  }
  // mapApiResponseToMapObject(mapData: any): DnDMap {
  //   return {
  //     description: mapData.description,
  //     events: mapData.events as DnDLocation[],
  //     imagePath: mapData.imagePath,
  //     name: mapData.strig,
  //     serial: mapData.serial,
  //     sizeX: mapData.sizeX,
  //     sizeY: mapData.sizeY
  //   }
  // }

}
