import { TestBed } from '@angular/core/testing';

import { ServiceComponentContactusService } from './service-component-contactus.service';

describe('ServiceComponentContactusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceComponentContactusService = TestBed.get(ServiceComponentContactusService);
    expect(service).toBeTruthy();
  });
});
