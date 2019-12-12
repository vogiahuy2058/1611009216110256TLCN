import { TestBed } from '@angular/core/testing';

import { MaterialpriceRestApiService } from './materialprice-rest-api.service';

describe('MaterialpriceRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MaterialpriceRestApiService = TestBed.get(MaterialpriceRestApiService);
    expect(service).toBeTruthy();
  });
});
