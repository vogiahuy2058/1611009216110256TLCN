import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdertypeCreateComponent } from './ordertype-create.component';

describe('OrdertypeCreateComponent', () => {
  let component: OrdertypeCreateComponent;
  let fixture: ComponentFixture<OrdertypeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdertypeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdertypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
