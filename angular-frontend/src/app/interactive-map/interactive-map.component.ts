/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-interactive-map',
  templateUrl: './interactive-map.component.html',
  styleUrls: ['./interactive-map.component.css']
})
export class InteractiveMapComponent implements OnInit {
  constructor(private sanitizer: DomSanitizer) {
  }
  @Input() parentMapSerial = "";

  transformedHtml: SafeHtml = 'This should not show';



  ngOnInit(): void {
    const apiUrl = 'http://localhost:8080/DnDEvolved/v1/maps/'
    const xmlPath = '../../assets/xml/locations.xml'
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
