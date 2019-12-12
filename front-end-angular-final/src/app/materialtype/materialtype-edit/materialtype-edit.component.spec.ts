import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialtypeEditComponent } from './materialtype-edit.component';

describe('MaterialtypeEditComponent', () => {
  let component: MaterialtypeEditComponent;
  let fixture: ComponentFixture<MaterialtypeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialtypeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialtypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
