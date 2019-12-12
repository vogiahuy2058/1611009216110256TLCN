import { TestBed } from '@angular/core/testing';

import { EmployeetypeRestApiService } from './employeetype-rest-api.service';

describe('EmployeetypeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmployeetypeRestApiService = TestBed.get(EmployeetypeRestApiService);
    expect(service).toBeTruthy();
  });
});
