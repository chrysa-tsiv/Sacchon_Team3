import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AverageCarbIntakeComponent } from './average-carb-intake.component';

describe('AverageCarbIntakeComponent', () => {
  let component: AverageCarbIntakeComponent;
  let fixture: ComponentFixture<AverageCarbIntakeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AverageCarbIntakeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AverageCarbIntakeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
