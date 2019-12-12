import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdertypeListComponent } from './ordertype-list.component';

describe('OrdertypeListComponent', () => {
  let component: OrdertypeListComponent;
  let fixture: ComponentFixture<OrdertypeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdertypeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdertypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
