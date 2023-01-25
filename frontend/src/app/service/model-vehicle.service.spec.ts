import { TestBed } from '@angular/core/testing';

import { ModelVehicleService } from './model-vehicle.service';

describe('ModelVehicleService', () => {
  let service: ModelVehicleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModelVehicleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
