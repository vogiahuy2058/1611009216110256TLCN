import { TestBed } from '@angular/core/testing';

import { InvoiceRestApiService } from './invoice-rest-api.service';

describe('InvoiceRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceRestApiService = TestBed.get(InvoiceRestApiService);
    expect(service).toBeTruthy();
  });
});
