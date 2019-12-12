import { TestBed } from '@angular/core/testing';

import { MaterialtypeRestApiService } from './materialtype-rest-api.service';

describe('MaterialtypeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MaterialtypeRestApiService = TestBed.get(MaterialtypeRestApiService);
    expect(service).toBeTruthy();
  });
});
