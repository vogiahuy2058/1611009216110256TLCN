import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeetypeListComponent } from './employeetype-list.component';

describe('EmployeetypeListComponent', () => {
  let component: EmployeetypeListComponent;
  let fixture: ComponentFixture<EmployeetypeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeetypeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeetypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
