import { TestBed } from '@angular/core/testing';

import { ApprovedoctorsService } from './approvedoctors.service';

describe('ApprovedoctorsService', () => {
  let service: ApprovedoctorsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApprovedoctorsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
