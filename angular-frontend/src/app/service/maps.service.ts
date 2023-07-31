import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { parseString } from 'xml2js';

import { map } from 'rxjs';
import { DnDMap } from '../interfaces/DnDMap';

@Injectable({
  providedIn: 'root'
})
export class MapService {
  private apiUrl = 'http://193.196.37.17:8080/DnDEvolved/v1/maps/'
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

  getMapBySerial(serial: string): Promise<DnDMap> {
    return new Promise((resolve, reject) => {
      this.http.get(this.apiUrl + serial, { responseType: 'text' })
        .pipe(
          map((xmlData: string) => {
            return new Promise<any>((resolevParse, rejectParse) => {
              parseString(xmlData, (err, result) => {
                if (err) {
                  rejectParse(err);
                } else {
                  resolevParse(result.Map);
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

  //delete map by serial endpoint
  deleteMap(serial: string): Promise<DnDMap> {
    return new Promise((resolve, reject) => {
      this.http.delete(this.apiUrl + serial, { responseType: 'text' })
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
}
