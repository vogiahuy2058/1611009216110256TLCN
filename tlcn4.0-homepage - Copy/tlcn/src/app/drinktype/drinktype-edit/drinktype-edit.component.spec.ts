import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrinktypeEditComponent } from './drinktype-edit.component';

describe('DrinktypeEditComponent', () => {
  let component: DrinktypeEditComponent;
  let fixture: ComponentFixture<DrinktypeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrinktypeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrinktypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
