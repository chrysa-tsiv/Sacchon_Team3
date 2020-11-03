import { TestBed } from '@angular/core/testing';

import { DoctorinfoService } from './doctorinfo.service';

describe('DoctorinfoService', () => {
  let service: DoctorinfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctorinfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
