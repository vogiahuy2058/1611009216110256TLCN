import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestpopupsComponent } from './testpopups.component';

describe('TestpopupsComponent', () => {
  let component: TestpopupsComponent;
  let fixture: ComponentFixture<TestpopupsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestpopupsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestpopupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
