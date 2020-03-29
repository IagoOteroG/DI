import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import {Art} from './art';
import {articulos} from './articulos-lista';

@Injectable({
  providedIn: 'root'
})
export class ArtService {
  constructor() { }

  getArticulos(): Observable<Art[]>{
    return of(articulos);
  }

  getArticulo(id: number){
    return of(articulos.find(Art => Art.id === id));
  }
}