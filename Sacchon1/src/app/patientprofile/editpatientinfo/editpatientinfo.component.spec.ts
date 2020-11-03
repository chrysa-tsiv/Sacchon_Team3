import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditpatientinfoComponent } from './editpatientinfo.component';

describe('EditpatientinfoComponent', () => {
  let component: EditpatientinfoComponent;
  let fixture: ComponentFixture<EditpatientinfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditpatientinfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditpatientinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
