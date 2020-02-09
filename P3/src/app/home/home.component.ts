import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {MatPaginator, MatTableDataSource, MatSort} from '@angular/material'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'weight'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  
   ngOnInit() {
    this.dataSource.paginator= this.paginator;
   }
 
 }
 export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  icon: string;
  
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'MSI', weight: 1000, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 2, name: 'Hydrogen', weight: 1400, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 3, name: 'MAC', weight: 1600, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 4, name: 'Windows', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 5, name: 'Proto', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 6, name: 'MSI', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 7, name: 'MAC', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 8, name: 'Windows', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 9, name: 'Lenovo', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'},
  {position: 10, name: 'Proto', weight: 1700, icon:'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.ZV0glKPRn-sWTtIHb0HYwQHaGx%26pid%3DApi&f=1'}



  
];