import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeactivatedraccountComponent } from './deactivatedraccount.component';

describe('DeactivatedraccountComponent', () => {
  let component: DeactivatedraccountComponent;
  let fixture: ComponentFixture<DeactivatedraccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeactivatedraccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeactivatedraccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
