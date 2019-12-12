import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeetypeCreateComponent } from './employeetype-create.component';

describe('EmployeetypeCreateComponent', () => {
  let component: EmployeetypeCreateComponent;
  let fixture: ComponentFixture<EmployeetypeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeetypeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeetypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
