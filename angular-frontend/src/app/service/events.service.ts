import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as xml2js from 'xml2js';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor(private http: HttpClient
             ) { }

  getEvents(): any {
    this.http.get('http://localhost:8080/DnDEvolved/v1/maps/1078cf56-8ec0-4af7-88c6-0775c9f8307c', { responseType: 'text' }).subscribe((xmlData: string) => {
        const p: xml2js.Parser = new xml2js.Parser();
        p.parseString(xmlData, (err, result) => {
          if (err) {
            throw err;
          }
          const json =JSON.stringify(result, null, 4);
          console.log("get events service stuff");
          console.log(json)

          return result.Map.Event;
        });
      });

  }
}
