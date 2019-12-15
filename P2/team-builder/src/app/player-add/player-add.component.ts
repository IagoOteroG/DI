import { Component, OnInit } from '@angular/core';
import { Player } from '../player';
import { PlayerService } from '../player.service';
import { Location } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { AppComponent } from '../app.component'
import { ReactiveFormsModule } from '@angular/forms';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-player-add',
  templateUrl: './player-add.component.html',
  styleUrls: ['./player-add.component.css']
})
export class PlayerAddComponent implements OnInit {

  players: Player[];
  Position="delantero";
  user:FormGroup;

  constructor(private playerService: PlayerService, 
    private location: Location) {AppComponent.hide()}

  ngOnInit() {
    this.getPlayers();
    this.user = new FormGroup({ 
    nombre: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]),  
    dorsal: new FormControl('', [Validators.required, Validators.maxLength(2),Validators.minLength(1) ]),   
    });
  }
  operacionSeleccionada: string = 'Portero';
  tipoOperaciones = [
    'Portero',
    'Defensa',
    'Medio',
    'Delantero',
  ];
 

  getPlayers(): void {
    this.playerService.getPlayers().subscribe(players => this.players = players);
  }
  

  add(name: string, dorsal:number, position:string): void {
    name = name.trim();
    position = this.Position;

    if (!name || !dorsal) { return; }
    if (isNaN(dorsal)){
      return;
    }
    this.playerService.addPlayer({ name,dorsal,position } as Player)
      .subscribe(player => {
        this.players.push(player);
      });
      this.location.back();
  }

  goBack(): void {
    this.location.back();
  }
}