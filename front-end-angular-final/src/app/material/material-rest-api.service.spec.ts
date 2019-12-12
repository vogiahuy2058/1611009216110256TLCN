import { TestBed } from '@angular/core/testing';

import { MaterialRestApiService } from './material-rest-api.service';

describe('MaterialRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MaterialRestApiService = TestBed.get(MaterialRestApiService);
    expect(service).toBeTruthy();
  });
});
