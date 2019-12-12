import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrinkpriceCreateComponent } from './drinkprice-create.component';

describe('DrinkpriceCreateComponent', () => {
  let component: DrinkpriceCreateComponent;
  let fixture: ComponentFixture<DrinkpriceCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrinkpriceCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrinkpriceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
