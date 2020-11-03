import { TestBed } from '@angular/core/testing';

import { EditpatientService } from './editpatient.service';

describe('EditpatientService', () => {
  let service: EditpatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditpatientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
