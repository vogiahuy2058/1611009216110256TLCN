import { TestBed } from '@angular/core/testing';

import { RestApiDrinkpriceService } from './rest-api-drinkprice.service';

describe('RestApiDrinkpriceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RestApiDrinkpriceService = TestBed.get(RestApiDrinkpriceService);
    expect(service).toBeTruthy();
  });
});
