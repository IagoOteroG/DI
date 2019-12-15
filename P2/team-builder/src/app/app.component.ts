import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Team Builder';
  static bool: boolean=true;
  static hide(){
    this.bool=false;
    
  }
  static show(){
    this.bool=true;
  }
  get Bool(){
    return AppComponent.bool;
  }
}
