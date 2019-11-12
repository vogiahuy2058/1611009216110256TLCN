import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialpriceEditComponent } from './materialprice-edit.component';

describe('MaterialpriceEditComponent', () => {
  let component: MaterialpriceEditComponent;
  let fixture: ComponentFixture<MaterialpriceEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialpriceEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialpriceEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
