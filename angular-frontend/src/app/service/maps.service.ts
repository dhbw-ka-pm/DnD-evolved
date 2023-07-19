import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { parseString } from 'xml2js';

import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private apiUrl = 'http://localhost:8080/DnDEvolved/v1/maps/'
  constructor(private http: HttpClient) {}

  // Create a method to fetch maps from the API

  getMaps(): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.get(this.apiUrl, { responseType: 'text' })
        .pipe(
          map((xmlData: string) => {
            return new Promise<any>((resolveParse, rejectParse) => {
              parseString(xmlData, (err, result) => {
                if (err) {
                  rejectParse(err);
                } else {
                  resolveParse(result.maps.map);
                }
              });
            });
          })
        )
        .subscribe(
          (maps: any) => {
            resolve(maps);
          },
          (error: any) => {
            reject(error);
          }
        );
    });
  }

  getMapBySerial(serial: string): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.get(this.apiUrl + serial, { responseType: 'text' })
        .pipe(
          map((xmlData: string) => {
            return new Promise<any>((resolevParse, rejectParse) => {
              parseString(xmlData, (err, result) => {
                if (err) {
                  rejectParse(err);
                } else {
                  resolevParse(result.map);
                }
              });
            });
          })
        )
        .subscribe(
          (map: any) => {
            resolve(map);
          },
          (error: any) => {
            reject(error);
          }
        );
    });
  }
}
