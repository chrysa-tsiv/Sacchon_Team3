import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrcalendarComponent } from './drcalendar.component';

describe('DrcalendarComponent', () => {
  let component: DrcalendarComponent;
  let fixture: ComponentFixture<DrcalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrcalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrcalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
