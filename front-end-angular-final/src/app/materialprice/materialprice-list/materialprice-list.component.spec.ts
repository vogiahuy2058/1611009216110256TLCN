import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialpriceListComponent } from './materialprice-list.component';

describe('MaterialpriceListComponent', () => {
  let component: MaterialpriceListComponent;
  let fixture: ComponentFixture<MaterialpriceListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialpriceListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialpriceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
