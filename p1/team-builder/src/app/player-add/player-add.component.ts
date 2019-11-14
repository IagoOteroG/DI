import { Component, OnInit } from '@angular/core';
import { Player } from '../player';
import { PlayerService } from '../player.service';
import { Location } from '@angular/common';
import { FormsModule } from '@angular/forms'


@Component({
  selector: 'app-player-add',
  templateUrl: './player-add.component.html',
  styleUrls: ['./player-add.component.css']
})
export class PlayerAddComponent implements OnInit {

  players: Player[];
  Position="delantero";

  constructor(private playerService: PlayerService, 
    private location: Location) { }

  ngOnInit() {
    this.getPlayers();
  }

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