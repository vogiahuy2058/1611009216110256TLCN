import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestpopupComponent } from './testpopup.component';

describe('TestpopupComponent', () => {
  let component: TestpopupComponent;
  let fixture: ComponentFixture<TestpopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestpopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestpopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
