import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArtService } from '../art.service';
import { Art } from '../art';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Comentario } from '../comentario-articulo';
import { ComentarioService } from '../comentario.service';
import { ComentarioArticuloService } from '../comentario-articulo.service';
import { MatCalendar } from '@angular/material';

@Component({
  selector: 'app-art-detail',
  templateUrl: './art-detail.component.html',
  styleUrls: ['./art-detail.component.css']
})
export class ArtDetailComponent implements OnInit {

  @Input() articulo: Art;

  form: FormGroup;
  comentarios: Comentario[] = [];
  cantidad: number = 0;

  constructor(
    private route: ActivatedRoute,
    private artService: ArtService,
    private comentarioService: ComentarioArticuloService
  ) { }

  ngOnInit() {
    this.getArticulo();
    this.getComentarios();


    this.form = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(150)]),
      comentario: new FormControl('', [Validators.required, Validators.minLength(5)])
    })
  }

  id: number;

  getArticulo(): void {
    this.id = +this.route.snapshot.paramMap.get('id');

    this.artService.getArticulo(this.id).subscribe(noticia => this.articulo = noticia);

  }

  getComentarios(): void {

    this.comentarioService.getComentarios(this.id).subscribe(comentarios => this.comentarios = comentarios.slice(0, 4));
    this.cantidad = this.comentarios.length;
  }

  submit(nombre: string, comentario: string) {

    var id = this.id;
    var fecha: number = new Date().getTime();

    this.comentarioService.add({ id, nombre, comentario, fecha } as Comentario);

    this.getComentarios();

    this.form.reset();
  }
}
