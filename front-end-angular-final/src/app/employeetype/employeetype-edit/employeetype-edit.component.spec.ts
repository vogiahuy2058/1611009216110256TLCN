import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeetypeEditComponent } from './employeetype-edit.component';

describe('EmployeetypeEditComponent', () => {
  let component: EmployeetypeEditComponent;
  let fixture: ComponentFixture<EmployeetypeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeetypeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeetypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
