import { TestBed } from '@angular/core/testing';

import { RecipeRestApiService } from './recipe-rest-api.service';

describe('RecipeRestApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecipeRestApiService = TestBed.get(RecipeRestApiService);
    expect(service).toBeTruthy();
  });
});
