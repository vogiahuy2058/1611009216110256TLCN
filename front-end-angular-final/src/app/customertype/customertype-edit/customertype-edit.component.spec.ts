import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomertypeEditComponent } from './customertype-edit.component';

describe('CustomertypeEditComponent', () => {
  let component: CustomertypeEditComponent;
  let fixture: ComponentFixture<CustomertypeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomertypeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomertypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
