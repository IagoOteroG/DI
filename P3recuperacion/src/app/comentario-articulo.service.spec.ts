import { TestBed } from '@angular/core/testing';

import { ComentarioArticuloService } from './comentario-articulo.service';

describe('ComentarioArticuloService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ComentarioArticuloService = TestBed.get(ComentarioArticuloService);
    expect(service).toBeTruthy();
  });
});
