import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../player';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PlayerService } from '../player.service';
import { AppComponent } from '../app.component'
import { FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {
  @Input() player: Player;
  
  user:FormGroup;

  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService,
    private location: Location

  ) { AppComponent.hide()}

  ngOnInit() {
    this.getPlayer();
    this.user = new FormGroup({ 
      nombre: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]),  
      dorsal: new FormControl('', [Validators.required, Validators.maxLength(2),Validators.minLength(1) ]),   
      });
  }

  getPlayer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.playerService.getPlayer(id).subscribe(player => this.player = player);
  }

  save(): void {
    this.playerService.updatePlayer(this.player)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
  operacionSeleccionada: string = 'Portero';
  tipoOperaciones = [
    'Portero',
    'Defensa',
    'Medio',
    'Delantero',
  ];
}
