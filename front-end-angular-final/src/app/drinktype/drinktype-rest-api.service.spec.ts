import { TestBed } from '@angular/core/testing';

import { DrinktypeRestApiService } from './drinktype-rest-api.service';

describe('DrinktypeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DrinktypeRestApiService = TestBed.get(DrinktypeRestApiService);
    expect(service).toBeTruthy();
  });
});
