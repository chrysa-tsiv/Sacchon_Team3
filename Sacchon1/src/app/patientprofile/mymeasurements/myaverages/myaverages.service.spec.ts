import { TestBed } from '@angular/core/testing';

import { MyaveragesService } from './myaverages.service';

describe('MyaveragesService', () => {
  let service: MyaveragesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyaveragesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
