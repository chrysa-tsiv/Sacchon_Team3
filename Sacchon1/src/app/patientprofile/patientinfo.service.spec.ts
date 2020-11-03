import { TestBed } from '@angular/core/testing';

import { PatientinfoService } from './patientinfo.service';

describe('PatientinfoService', () => {
  let service: PatientinfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientinfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
