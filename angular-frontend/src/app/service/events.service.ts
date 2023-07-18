import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import * as xml2js from 'xml2js';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor(private http: HttpClient
  ) {
  }

  getEvents(): any {
    const contentType = new HttpHeaders().set('Content-Type', 'application/xml');
    this.http.get('http://localhost:8080/DnDEvolved/v1/maps/1078cf56-8ec0-4af7-88c6-0775c9f8307c', { headers: contentType, responseType: "text" }).subscribe(
      response => {
        const p: xml2js.Parser = new xml2js.Parser();
        p.parseString(response, (err, result) => {
          if (err) {
            throw err;
          }
          const json = JSON.stringify(result, null, 4);
          console.log("get events service stuff");
          console.log(json);
          console.log(response);
          return result.Map.Event;
        });
      }
      , // Handle success here
      error => console.log(error) // Handle error here
    );

  }
}
