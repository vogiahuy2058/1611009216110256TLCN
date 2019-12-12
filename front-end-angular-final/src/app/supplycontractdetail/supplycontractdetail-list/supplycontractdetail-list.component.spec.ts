import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplycontractdetailListComponent } from './supplycontractdetail-list.component';

describe('SupplycontractdetailListComponent', () => {
  let component: SupplycontractdetailListComponent;
  let fixture: ComponentFixture<SupplycontractdetailListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplycontractdetailListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplycontractdetailListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
