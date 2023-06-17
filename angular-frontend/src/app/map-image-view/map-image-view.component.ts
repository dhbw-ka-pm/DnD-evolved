import { Component, ElementRef, ViewChild } from '@angular/core';


// const maxLocations = 5;


@Component({
  selector: 'app-map-image-view',
  templateUrl: './map-image-view.component.html',
  styleUrls: ['./map-image-view.component.css']
})
export class MapImageViewComponent
// implements AfterViewInit
{
  @ViewChild('imageElement') imageElement!: ElementRef;
  locations: { x: number, y: number }[] = [
    { x: 100, y: 50 },
    { x: 200, y: 150 },
    { x: 300, y: 250 },
    { x: 400, y: 350 },
    { x: 500, y: 450 }
  ];


  toggleFullscreen() {
    const image = this.imageElement.nativeElement as HTMLImageElement;
    if (!document.fullscreenElement) {
      image.requestFullscreen();
    } else {
      if (document.exitFullscreen) {
        document.exitFullscreen();
      }
    }
  }

  // ngAfterViewInit(): void {
  //   this.generateRandomLocations();
  // }

  // generateRandomLocations() {
  //   const image = this.imageElement.nativeElement as HTMLImageElement;
  //   const imageWidth = image.clientWidth;
  //   const imageHeight = image.clientHeight;
  //   for (let i = 0; i < maxLocations; i++) {
  //     const x = Math.random() * imageWidth;
  //     const y = Math.random() * imageHeight;
  //     this.locations.push({ x, y });
  //   }
  // }

}

