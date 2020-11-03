import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditdoctorinfoComponent } from './editdoctorinfo.component';

describe('EditdoctorinfoComponent', () => {
  let component: EditdoctorinfoComponent;
  let fixture: ComponentFixture<EditdoctorinfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditdoctorinfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditdoctorinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
