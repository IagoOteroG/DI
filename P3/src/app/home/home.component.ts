import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

 public colSize=4;
 public isMobile: boolean =false;
 constructor(breakpointObserver: BreakpointObserver){
   breakpointObserver.observe([Breakpoints.Handset]).subscribe(result => {
     this.isMobile = result.matches;
     if(this.isMobile){
       this.colSize=1;
     }else{
       this.colSize=4;
     }
   });
 }
  ngOnInit() {
  }

}
