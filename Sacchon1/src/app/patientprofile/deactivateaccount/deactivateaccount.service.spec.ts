import { TestBed } from '@angular/core/testing';

import { DeactivateaccountService } from './deactivateaccount.service';

describe('DeactivateaccountService', () => {
  let service: DeactivateaccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeactivateaccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
