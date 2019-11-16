import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplycontractEditComponent } from './supplycontract-edit.component';

describe('SupplycontractEditComponent', () => {
  let component: SupplycontractEditComponent;
  let fixture: ComponentFixture<SupplycontractEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplycontractEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplycontractEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
