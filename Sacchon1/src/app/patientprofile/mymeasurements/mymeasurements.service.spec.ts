import { TestBed } from '@angular/core/testing';

import { MymeasurementsService } from './mymeasurements.service';

describe('MymeasurementsService', () => {
  let service: MymeasurementsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MymeasurementsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
