import { Component, OnInit, ViewChild } from '@angular/core';
import { articulos } from '../articulos-lista';
import { Art } from '../art';
import { ArtService } from '../art.service';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {MatPaginator, MatTableDataSource, MatSort, MatDrawerContent} from '@angular/material'


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  articulos: Art[] = [];
  heigh: string;
  artside: Art[] = [];
  breakpoint: number;
  va2: number = 8;
  va1: number = 4;
  constructor(private artService: ArtService) { }
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  ngOnInit() {
    this.getArticulos();
    if (screen.width < 415) {
      this.breakpoint = 1;
    } else if (screen.width > 415 && screen.width < 1150) {
      this.breakpoint = 2
    } else {
      this.breakpoint = 4
    }

  }
  more() {
    this.va1 = this.va1 + 4;
    this.va2 = this.va2 + 4;
    if (screen.width < 415) {
      this.artService.getArticulos().subscribe(ar => this.articulos = articulos.slice(0, this.va1));
    } else if (screen.width > 415 && screen.width < 1150) {
      this.artService.getArticulos().subscribe(articulos => this.articulos = articulos.slice(0, this.va1));
    } else {
      this.artService.getArticulos().subscribe(articulos => this.articulos = articulos.slice(0, this.va2));
    }
  }

  onResize(event) {
    if (screen.width < 415) {
      this.breakpoint = 1;
    } else if (screen.width > 415 && screen.width < 1150) {
      this.breakpoint = 2
    } else {
      this.breakpoint = 4
    }
  }
 
  getArticulos(): void {
    if (screen.width < 415) {
      this.artService.getArticulos().subscribe(articulos => this.articulos = articulos.slice(0, this.va1));
    } else if (screen.width > 415 && screen.width < 1150) {
      this.artService.getArticulos().subscribe(articulos => this.articulos = articulos.slice(0, this.va2));
    } else {
      this.artService.getArticulos().subscribe(articulos => this.articulos = articulos.slice(0, this.va2));
    }
    this.artService.getArticulos().subscribe(articulos => this.artside = articulos.slice(0, 5));
    if (screen.width < 415) {
      this.heigh = "1:1";
    } else if (screen.width > 415 && screen.width < 1150) {
      this.heigh = "1:1.1";
    } else {
      this.heigh = "1:1";
    }
  }
}