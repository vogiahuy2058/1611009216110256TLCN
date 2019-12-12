import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrinktypeCreateComponent } from './drinktype-create.component';

describe('DrinktypeCreateComponent', () => {
  let component: DrinktypeCreateComponent;
  let fixture: ComponentFixture<DrinktypeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrinktypeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrinktypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
