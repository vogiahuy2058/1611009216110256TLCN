import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplycontractdetailEditComponent } from './supplycontractdetail-edit.component';

describe('SupplycontractdetailEditComponent', () => {
  let component: SupplycontractdetailEditComponent;
  let fixture: ComponentFixture<SupplycontractdetailEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplycontractdetailEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplycontractdetailEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
