import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CatalogComponent } from './catalog/catalog.component';


const routes: Routes = [
  { path: '', redirectTo: '/homeComponent', pathMatch: 'full' },
  { path: 'catalog', component: CatalogComponent  },
  { path: 'home', component: HomeComponent  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
