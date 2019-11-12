import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialpriceCreateComponent } from './materialprice-create.component';

describe('MaterialpriceCreateComponent', () => {
  let component: MaterialpriceCreateComponent;
  let fixture: ComponentFixture<MaterialpriceCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialpriceCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialpriceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
