import { TestBed } from '@angular/core/testing';

import { BrandVehicleService } from './brand-vehicle.service';

describe('BrandVehicleService', () => {
  let service: BrandVehicleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BrandVehicleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
