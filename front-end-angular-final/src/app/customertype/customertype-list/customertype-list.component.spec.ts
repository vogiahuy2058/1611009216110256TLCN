import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomertypeListComponent } from './customertype-list.component';

describe('CustomertypeListComponent', () => {
  let component: CustomertypeListComponent;
  let fixture: ComponentFixture<CustomertypeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomertypeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomertypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
