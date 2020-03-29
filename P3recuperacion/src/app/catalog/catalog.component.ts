import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  public colSize=4;
  public maxPx=550;
  public isMobile: boolean =false;
  constructor(breakpointObserver: BreakpointObserver){
    breakpointObserver.observe([Breakpoints.Handset]).subscribe(result => {
      this.isMobile = result.matches;
      if(this.isMobile){
        this.colSize=1;
        this.maxPx=490;
      }else{
        this.colSize=4;
        this.maxPx=550;
      }
    });
  }
   ngOnInit() {
   }
 
 }