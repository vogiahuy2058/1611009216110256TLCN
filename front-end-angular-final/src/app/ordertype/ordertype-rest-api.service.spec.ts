import { TestBed } from '@angular/core/testing';

import { OrdertypeRestApiService } from './ordertype-rest-api.service';

describe('OrdertypeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrdertypeRestApiService = TestBed.get(OrdertypeRestApiService);
    expect(service).toBeTruthy();
  });
});
