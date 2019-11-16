import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabletypeEditComponent } from './tabletype-edit.component';

describe('TabletypeEditComponent', () => {
  let component: TabletypeEditComponent;
  let fixture: ComponentFixture<TabletypeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabletypeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabletypeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
