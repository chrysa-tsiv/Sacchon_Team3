import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyaveragesComponent } from './myaverages.component';

describe('MyaveragesComponent', () => {
  let component: MyaveragesComponent;
  let fixture: ComponentFixture<MyaveragesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyaveragesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyaveragesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
