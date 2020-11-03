import { TestBed } from '@angular/core/testing';

import { NewconsultationService } from './newconsultation.service';

describe('NewconsultationService', () => {
  let service: NewconsultationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewconsultationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
