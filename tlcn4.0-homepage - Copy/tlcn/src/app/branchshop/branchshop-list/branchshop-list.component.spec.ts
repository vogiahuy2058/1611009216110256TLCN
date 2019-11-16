import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchshopListComponent } from './branchshop-list.component';

describe('BranchshopListComponent', () => {
  let component: BranchshopListComponent;
  let fixture: ComponentFixture<BranchshopListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchshopListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchshopListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
