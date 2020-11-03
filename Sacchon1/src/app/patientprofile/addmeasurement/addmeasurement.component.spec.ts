import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddmeasurementComponent } from './addmeasurement.component';

describe('AddmeasurementComponent', () => {
  let component: AddmeasurementComponent;
  let fixture: ComponentFixture<AddmeasurementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddmeasurementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddmeasurementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
