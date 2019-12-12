import { TestBed } from '@angular/core/testing';

import { DrinkRestApiService } from './drink-rest-api.service';

describe('DrinkRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DrinkRestApiService = TestBed.get(DrinkRestApiService);
    expect(service).toBeTruthy();
  });
});
