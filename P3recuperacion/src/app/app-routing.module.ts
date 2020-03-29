import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactusComponent } from './contactus/contactus.component';
import { WhereweareComponent } from './whereweare/whereweare.component';
import { WhoweareComponent } from './whoweare/whoweare.component';
import { CatalogComponent } from './catalog/catalog.component';
import { ArtDetailComponent } from './art-detail/art-detail.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'catalog', component: CatalogComponent  },
  { path: 'whoweare', component: WhoweareComponent  },
  { path: 'whereweare', component: WhereweareComponent  },
  { path: 'contactus', component: ContactusComponent  },
  { path: 'home', component: HomeComponent  },
  { path: 'detalles/:id', component: ArtDetailComponent  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
