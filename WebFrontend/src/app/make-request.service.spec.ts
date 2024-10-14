import { TestBed } from '@angular/core/testing';

import { MakeRequestService } from './make-request.service';

describe('MakeRequestService', () => {
  let service: MakeRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MakeRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
