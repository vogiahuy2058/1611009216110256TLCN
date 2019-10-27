import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdertypeEditComponent } from './ordertype-edit.component';

describe('OrdertypeEditComponent', () => {
  let component: OrdertypeEditComponent;
  let fixture: ComponentFixture<OrdertypeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdertypeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdertypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
