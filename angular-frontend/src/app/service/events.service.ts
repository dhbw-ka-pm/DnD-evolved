import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { parseString } from 'xml2js';
import {DnDEvent} from "../interfaces/DnDEvent";

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor(private http: HttpClient
  ) {
  }

  ;

  getEvents(serial: string): Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8080/DnDEvolved/v1/maps/events/' + serial, { responseType: 'text' })
        .pipe(
          map((xmlData: string) => {
            return new Promise<any>((resolveParse, rejectParse) => {
              parseString(xmlData, (err, result) => {
                if (err) {
                  rejectParse(err);
                } else {
                  resolveParse(result.events.event);
                }
              });
            });
          })
        )
        .subscribe(
          (events: any) => {
            resolve(events);
          },
          (error: any) => {
            reject(error);
          }
        );
    });
  }

  getEventData(serial: string): Promise<DnDEvent> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8080/DnDEvolved/v1/events/' + serial, { responseType: 'text' })
        .pipe(
          map((xmlData: string) => {
            return new Promise<any>((resolveParse, rejectParse) => {
              parseString(xmlData, (err, result) => {
                if (err) {
                  rejectParse(err);
                } else {
                  resolveParse(result.Event);
                }
              });
            });
          })
        )
        .subscribe(
          (events: any) => {
            resolve(events);
          },
          (error: any) => {
            reject(error);
          }
        );
    });
  }
}
