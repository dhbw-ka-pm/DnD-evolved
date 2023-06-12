import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapImageViewComponent } from "./MapImageViewComponent";

describe('MapImageViewComponent', () => {
  let component: MapImageViewComponent;
  let fixture: ComponentFixture<MapImageViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapImageViewComponent]
    });
    fixture = TestBed.createComponent(MapImageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
