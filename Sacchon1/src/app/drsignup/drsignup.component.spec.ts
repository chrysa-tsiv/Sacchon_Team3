import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrsignupComponent } from './drsignup.component';

describe('DrsignupComponent', () => {
  let component: DrsignupComponent;
  let fixture: ComponentFixture<DrsignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrsignupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrsignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
