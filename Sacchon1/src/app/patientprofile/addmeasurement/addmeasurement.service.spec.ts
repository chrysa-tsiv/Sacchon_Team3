import { TestBed } from '@angular/core/testing';

import { AddmeasurementService } from './addmeasurement.service';

describe('AddmeasurementService', () => {
  let service: AddmeasurementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddmeasurementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
