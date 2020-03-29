import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { WhoweareComponent } from './whoweare/whoweare.component';
import { WhereweareComponent } from './whereweare/whereweare.component';
import { CatalogComponent } from './catalog/catalog.component';
import { ContactusComponent } from './contactus/contactus.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';  
import {MatGridListModule} from '@angular/material/grid-list'; 
import {MatFormFieldModule} from '@angular/material/form-field'; 
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatSliderModule } from '@angular/material/slider';
import { FooterComponent } from './footer/footer.component';
import { FooterSComponent } from './footer-s/footer-s.component';
import {MatPaginatorModule} from '@angular/material/paginator'; 
import {MatTableModule} from '@angular/material/table';
import { ArtDetailComponent } from './art-detail/art-detail.component'; 






@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    WhoweareComponent,
    WhereweareComponent,
    CatalogComponent,
    ContactusComponent,
    MainNavComponent,
    FooterComponent,
    FooterSComponent,
    ArtDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatGridListModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatPaginatorModule,
    MatTableModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
