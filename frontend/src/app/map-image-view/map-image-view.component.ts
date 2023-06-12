import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-map-image-view',
  templateUrl: './map-image-view.component.html',
  styleUrls: ['./map-image-view.component.css']
})
export class MapImageViewComponent {
  @ViewChild('imageElement') imageElement!: ElementRef;
  locations: { x: number, y: number }[] = [];

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

  ngOnInit() {
    this.generateRandomLocations();
  }

  generateRandomLocations() {
    const image = this.imageElement.nativeElement as HTMLImageElement;
    for (let i = 0; i < 5; i++) {
      const x = Math.random() * image.width;
      const y = Math.random() * image.height;
      this.locations.push({ x, y });
    }
  }

}
