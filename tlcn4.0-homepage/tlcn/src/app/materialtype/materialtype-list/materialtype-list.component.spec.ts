import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialtypeListComponent } from './materialtype-list.component';

describe('MaterialtypeListComponent', () => {
  let component: MaterialtypeListComponent;
  let fixture: ComponentFixture<MaterialtypeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialtypeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialtypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
