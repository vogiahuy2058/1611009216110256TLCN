import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchshopCreateComponent } from './branchshop-create.component';

describe('BranchshopCreateComponent', () => {
  let component: BranchshopCreateComponent;
  let fixture: ComponentFixture<BranchshopCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchshopCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchshopCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
