import { TestBed } from '@angular/core/testing';

import { TabletypeRestApiService } from './tabletype-rest-api.service';

describe('TabletypeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TabletypeRestApiService = TestBed.get(TabletypeRestApiService);
    expect(service).toBeTruthy();
  });
});
