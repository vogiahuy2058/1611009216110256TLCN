import { TestBed } from '@angular/core/testing';

import { EmployeeRestApiService } from './employee-rest-api.service';

describe('EmployeeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmployeeRestApiService = TestBed.get(EmployeeRestApiService);
    expect(service).toBeTruthy();
  });
});
