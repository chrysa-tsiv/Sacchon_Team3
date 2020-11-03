import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AveragGlucoseLevelComponent } from './averag-glucose-level.component';

describe('AveragGlucoseLevelComponent', () => {
  let component: AveragGlucoseLevelComponent;
  let fixture: ComponentFixture<AveragGlucoseLevelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AveragGlucoseLevelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AveragGlucoseLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
