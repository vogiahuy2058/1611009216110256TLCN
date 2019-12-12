import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchshopEditComponent } from './branchshop-edit.component';

describe('BranchshopEditComponent', () => {
  let component: BranchshopEditComponent;
  let fixture: ComponentFixture<BranchshopEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchshopEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchshopEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
