import { TestBed } from '@angular/core/testing';

import { CustomerRestApiService } from './customer-rest-api.service';

describe('CustomerRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomerRestApiService = TestBed.get(CustomerRestApiService);
    expect(service).toBeTruthy();
  });
});
