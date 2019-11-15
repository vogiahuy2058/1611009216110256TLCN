import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplycontractListComponent } from './supplycontract-list.component';

describe('SupplycontractListComponent', () => {
  let component: SupplycontractListComponent;
  let fixture: ComponentFixture<SupplycontractListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplycontractListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplycontractListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
