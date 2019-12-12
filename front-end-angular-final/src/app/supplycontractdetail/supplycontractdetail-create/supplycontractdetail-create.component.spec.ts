import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplycontractdetailCreateComponent } from './supplycontractdetail-create.component';

describe('SupplycontractdetailCreateComponent', () => {
  let component: SupplycontractdetailCreateComponent;
  let fixture: ComponentFixture<SupplycontractdetailCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplycontractdetailCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplycontractdetailCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
