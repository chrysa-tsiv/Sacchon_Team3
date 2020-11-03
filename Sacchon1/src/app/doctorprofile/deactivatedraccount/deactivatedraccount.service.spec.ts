import { TestBed } from '@angular/core/testing';

import { DeactivatedraccountService } from './deactivatedraccount.service';

describe('DeactivatedraccountService', () => {
  let service: DeactivatedraccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeactivatedraccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
