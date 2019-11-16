import { TestBed } from '@angular/core/testing';

import { SupplierRestApiService } from './supplier-rest-api.service';

describe('SupplierRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SupplierRestApiService = TestBed.get(SupplierRestApiService);
    expect(service).toBeTruthy();
  });
});
