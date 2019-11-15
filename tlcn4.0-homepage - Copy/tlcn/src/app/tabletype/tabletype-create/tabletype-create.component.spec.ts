import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabletypeCreateComponent } from './tabletype-create.component';

describe('TabletypeCreateComponent', () => {
  let component: TabletypeCreateComponent;
  let fixture: ComponentFixture<TabletypeCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabletypeCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabletypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
