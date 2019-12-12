import { TestBed } from '@angular/core/testing';

import { UnitRestApiService } from './unit-rest-api.service';

describe('UnitRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UnitRestApiService = TestBed.get(UnitRestApiService);
    expect(service).toBeTruthy();
  });
});
