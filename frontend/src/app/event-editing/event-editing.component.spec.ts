import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventEditingComponent } from './event-editing.component';

describe('EventEditingComponent', () => {
  let component: EventEditingComponent;
  let fixture: ComponentFixture<EventEditingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventEditingComponent]
    });
    fixture = TestBed.createComponent(EventEditingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
