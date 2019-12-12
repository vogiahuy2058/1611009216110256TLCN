import { TestBed } from '@angular/core/testing';

import { AccountRestApiService } from './account-rest-api.service';

describe('AccountRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccountRestApiService = TestBed.get(AccountRestApiService);
    expect(service).toBeTruthy();
  });
});
