import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MymeasurementsComponent } from './mymeasurements.component';

describe('MymeasurementsComponent', () => {
  let component: MymeasurementsComponent;
  let fixture: ComponentFixture<MymeasurementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MymeasurementsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MymeasurementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
