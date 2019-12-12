import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplycontractCreateComponent } from './supplycontract-create.component';

describe('SupplycontractCreateComponent', () => {
  let component: SupplycontractCreateComponent;
  let fixture: ComponentFixture<SupplycontractCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplycontractCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplycontractCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
