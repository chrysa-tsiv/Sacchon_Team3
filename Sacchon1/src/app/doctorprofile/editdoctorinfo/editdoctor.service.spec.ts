import { TestBed } from '@angular/core/testing';

import { EditdoctorService } from './editdoctor.service';

describe('EditdoctorService', () => {
  let service: EditdoctorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditdoctorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
