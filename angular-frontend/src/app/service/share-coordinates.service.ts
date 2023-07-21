import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShareCoordinatesService {
  x = '0'
  y = '0'

  // eslint-disable-next-line @typescript-eslint/no-empty-function
  constructor() {}
  setLocation(x: any, y: any): void {
    this.x = x; this.y = y
  }
  getLocation(): any[] {
    return [this.x, this.y]
  }
}
