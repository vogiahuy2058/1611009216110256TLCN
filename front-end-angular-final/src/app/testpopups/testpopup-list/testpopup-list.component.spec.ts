import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestpopupListComponent } from './testpopup-list.component';

describe('TestpopupListComponent', () => {
  let component: TestpopupListComponent;
  let fixture: ComponentFixture<TestpopupListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestpopupListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestpopupListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
