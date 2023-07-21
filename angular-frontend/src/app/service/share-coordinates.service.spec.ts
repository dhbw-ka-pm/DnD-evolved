import { TestBed } from '@angular/core/testing';

import { ShareCoordinatesService } from './share-coordinates.service';

describe('ShareCoordinatesService', () => {
  let service: ShareCoordinatesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShareCoordinatesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
