import { TestBed } from '@angular/core/testing';

import { SupplycontractRestApiService } from './supplycontract-rest-api.service';

describe('SupplycontractRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SupplycontractRestApiService = TestBed.get(SupplycontractRestApiService);
    expect(service).toBeTruthy();
  });
});
