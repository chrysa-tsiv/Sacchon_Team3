import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalinfotableComponent } from './personalinfotable.component';

describe('PersonalinfotableComponent', () => {
  let component: PersonalinfotableComponent;
  let fixture: ComponentFixture<PersonalinfotableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalinfotableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalinfotableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
