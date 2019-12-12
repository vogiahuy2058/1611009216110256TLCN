import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialtypeCreateComponent } from './materialtype-create.component';

describe('MaterialtypeCreateComponent', () => {
  let component: MaterialtypeCreateComponent;
  let fixture: ComponentFixture<MaterialtypeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialtypeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialtypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
