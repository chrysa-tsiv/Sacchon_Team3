import { TestBed } from '@angular/core/testing';

import { MypatientsService } from './mypatients.service';

describe('MypatientsService', () => {
  let service: MypatientsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MypatientsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
