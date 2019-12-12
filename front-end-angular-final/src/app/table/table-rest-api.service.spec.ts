import { TestBed } from '@angular/core/testing';

import { TableRestApiService } from './table-rest-api.service';

describe('TableRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TableRestApiService = TestBed.get(TableRestApiService);
    expect(service).toBeTruthy();
  });
});
