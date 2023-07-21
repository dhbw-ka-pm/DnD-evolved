/* eslint-disable @typescript-eslint/no-explicit-any */
import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-interactive-map',
  templateUrl: './interactive-map.component.html',
  styleUrls: ['./interactive-map.component.css']
})
export class InteractiveMapComponent implements OnInit, AfterViewInit {
  handleKeyboardEvent(event: KeyboardEvent) {
    console.log();
  }

  getImageCoordinates(event: MouseEvent): number[] {
    const transformedHtmlElement = document.getElementById('transformedHtml')?.querySelector('svg');
    if (transformedHtmlElement === null) {
      console.log('Transformed HTML is not available yet.');
      return [0, 0]
    } else {
      const boundingRect = transformedHtmlElement?.getBoundingClientRect();

      // Calculate the x and y coordinates relative to the transformedHtml div.
      if (boundingRect) {
        const x = event.clientX - boundingRect.left - 30;
        const y = event.clientY - boundingRect.top - 53;
        console.log('X Coordinate:', x);
        console.log('Y Coordinate:', y);
        return [x, y]
      }
      return [0, 0]
    }
  }
  constructor(private sanitizer: DomSanitizer) {
  }
  ngAfterViewInit(): void {
    console.log();
  }
  @Input() parentMapSerial = "";

  transformedHtml: SafeHtml = 'This should not show';



  ngOnInit(): void {
    const apiUrl = 'http://localhost:8080/DnDEvolved/v1/maps/'
    const xslPath = '../../assets/xslt/interactive-map/interactiveMap.xslt'

    console.log("child serial: " + this.parentMapSerial);
    console.log("path: " + apiUrl + this.parentMapSerial);
    const xhttp = new XMLHttpRequest();

    xhttp.open('GET', apiUrl + this.parentMapSerial, false);
    // xhttp.open('GET', xmlPath, false);
    xhttp.send();
    const xml: any = xhttp.responseXML;
    console.log(xml);

    xhttp.open('GET', xslPath, false);
    xhttp.send();
    const xsl: any = xhttp.responseXML;
    const processor = new XSLTProcessor();
    processor.importStylesheet(xsl);
    const result = processor.transformToDocument(xml);
    this.transformedHtml = this.sanitizer.bypassSecurityTrustHtml(new XMLSerializer().serializeToString(result.documentElement));
    console.log(this.transformedHtml);

  }


}
