import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-interactive-map',
  templateUrl: './interactive-map.component.html',
  styleUrls: ['./interactive-map.component.css']
})
export class InteractiveMapComponent implements OnInit {
  constructor(private sanitizer: DomSanitizer) {
  }

  transformedHtml: SafeHtml = 'This should not show';



  ngOnInit(): void {
    const xmlPath = '../../assets/xml/locations.xml'
    const xslPath = '../../assets/xslt/interactive-map/interactiveMap.xslt'

    const xhttp = new XMLHttpRequest();

    // xhttp.onreadystatechange = () => {
    // }
    xhttp.open('GET', xmlPath, false);
    xhttp.send();
    const xml: any = xhttp.responseXML;

    xhttp.open('GET', xslPath, false);
    xhttp.send();
    const xsl: any = xhttp.responseXML;
    const processor = new XSLTProcessor();
    processor.importStylesheet(xsl);
    const result = processor.transformToDocument(xml);
    this.transformedHtml = this.sanitizer.bypassSecurityTrustHtml(new XMLSerializer().serializeToString(result.documentElement));
    // this.transformedHtml=result) ;
    console.log(this.transformedHtml);
  }


}
