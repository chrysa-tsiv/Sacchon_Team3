import { TestBed } from '@angular/core/testing';

import { DrsignupService } from './drsignup.service';

describe('DrsignupService', () => {
  let service: DrsignupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrsignupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
