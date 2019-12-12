import { TestBed } from '@angular/core/testing';

import { CustomertypeRestApiService } from './customertype-rest-api.service';

describe('CustomertypeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomertypeRestApiService = TestBed.get(CustomertypeRestApiService);
    expect(service).toBeTruthy();
  });
});
