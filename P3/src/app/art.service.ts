import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import {Art} from './art';
import {articulos} from './articulos';

@Injectable({
  providedIn: 'root'
})
export class ArtService {


  constructor() { }

  getNoticias(): Observable<Art[]>{
    return of(articulos);
  }

  getNoticia(position: number){
    return of(articulos.find(Art => Art.position === position));
  }
}