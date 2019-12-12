import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrinktypeListComponent } from './drinktype-list.component';

describe('DrinktypeListComponent', () => {
  let component: DrinktypeListComponent;
  let fixture: ComponentFixture<DrinktypeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrinktypeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrinktypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
