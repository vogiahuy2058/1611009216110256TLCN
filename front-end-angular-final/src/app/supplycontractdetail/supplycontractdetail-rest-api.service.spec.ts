import { TestBed } from '@angular/core/testing';

import { SupplycontractdetailRestApiService } from './supplycontractdetail-rest-api.service';

describe('SupplycontractdetailRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SupplycontractdetailRestApiService = TestBed.get(SupplycontractdetailRestApiService);
    expect(service).toBeTruthy();
  });
});
