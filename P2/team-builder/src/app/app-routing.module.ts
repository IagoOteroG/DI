import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayersComponent } from './players/players.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';
import { PlayerAddComponent} from './player-add/player-add.component';
import { HomeComponentComponent} from './home-component/home-component.component';

const routes: Routes = [
  { path: '', redirectTo: '/homeComponent', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: PlayerDetailComponent },
  { path: 'players', component: PlayersComponent },
  { path: 'player-add', component: PlayerAddComponent},
  { path: 'homeComponent', component: HomeComponentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
