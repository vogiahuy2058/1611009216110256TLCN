import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabletypeListComponent } from './tabletype-list.component';

describe('TabletypeListComponent', () => {
  let component: TabletypeListComponent;
  let fixture: ComponentFixture<TabletypeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabletypeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabletypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
