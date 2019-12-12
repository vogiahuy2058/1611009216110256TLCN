import { TestBed } from '@angular/core/testing';

import { BranchshopRestApiService } from './branchshop-rest-api.service';

describe('BranchshopRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BranchshopRestApiService = TestBed.get(BranchshopRestApiService);
    expect(service).toBeTruthy();
  });
});
