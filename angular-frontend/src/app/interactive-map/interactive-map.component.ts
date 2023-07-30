/* eslint-disable @typescript-eslint/no-explicit-any */
import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { ShareCoordinatesService } from '../service/share-coordinates.service';

@Component({
  selector: 'app-interactive-map',
  templateUrl: './interactive-map.component.html',
  styleUrls: ['./interactive-map.component.css']
})
export class InteractiveMapComponent implements OnInit, AfterViewInit {
  handleKeyboardEvent(event: KeyboardEvent) {
    console.log();
  }

  constructor(private sanitizer: DomSanitizer,
    private shareCoordinates: ShareCoordinatesService,
  ) {
  }

  getImageCoordinates(event: MouseEvent): void {
    const transformedHtmlElement = document.getElementById('transformedHtml')?.querySelector('svg');
    if (transformedHtmlElement === null) {
      console.log('Transformed HTML is not available yet.');
    } else {
      const boundingRect = transformedHtmlElement?.getBoundingClientRect();

      // Calculate the x and y coordinates relative to the transformedHtml div.
      if (boundingRect) {
        const x = event.clientX - boundingRect.left - 30;
        const y = event.clientY - boundingRect.top - 53;
        console.log('X Coordinate:', x);
        console.log('Y Coordinate:', y);
        this.shareCoordinates.setLocation(x, y)

      }
    }
  }
  ngAfterViewInit(): void {
    console.log();
  }
  @Input() parentMapSerial = "";

  transformedHtml: SafeHtml = 'This should not show';
  //save the content of an xslt file as a string
  xsltContent = `<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <!-- <html> -->
    <!--   <body> -->

    <svg
      version="1.1"
      width="1000" height="1000"
      xmlns="http://www.w3.org/2000/svg">
      <g>
        <image x="0" y="0">
          <xsl:attribute name="href">
            <xsl:value-of select="Map/ImagePath"></xsl:value-of>
          </xsl:attribute>
        </image>

      </g>
      <g>
        <xsl:for-each select="/Map/Event/entry/value">

          <image href="/assets/icons/mappointer-v1.svg" width="60" height="60">
            <xsl:attribute name="x">
              <xsl:value-of select="x"></xsl:value-of>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="y"></xsl:value-of>
            </xsl:attribute>

          </image>

        </xsl:for-each>

      </g>

    </svg>
    <!--   </body> -->

    <!-- </html> -->

  </xsl:template>
</xsl:stylesheet>

`





  ngOnInit(): void {
    const domParser = new DOMParser();
    const xsltDoc = domParser.parseFromString(this.xsltContent, 'text/xml');
    console.log('xsltDoc: ' + xsltDoc);
    const apiUrl = 'http://localhost:8080/DnDEvolved/v1/maps/'

    console.log("child serial: " + this.parentMapSerial);
    console.log("path: " + apiUrl + this.parentMapSerial);
    const xhttp = new XMLHttpRequest();

    xhttp.open('GET', apiUrl + this.parentMapSerial, false);
    // xhttp.open('GET', xmlPath, false);
    xhttp.send();
    const xml: any = xhttp.responseXML;
    console.log(xml);

    const processor = new XSLTProcessor();
    processor.importStylesheet(xsltDoc);
    const result = processor.transformToDocument(xml);
    this.transformedHtml = this.sanitizer.bypassSecurityTrustHtml(new XMLSerializer().serializeToString(result.documentElement));
    console.log(this.transformedHtml);

  }


}
