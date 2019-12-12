import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomertypeCreateComponent } from './customertype-create.component';

describe('CustomertypeCreateComponent', () => {
  let component: CustomertypeCreateComponent;
  let fixture: ComponentFixture<CustomertypeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomertypeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomertypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
