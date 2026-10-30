import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-autores',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './Autores.html'
})
export class Autores implements OnInit {
  autores: any[] = [];
  nuevoAutor: any = { nombre: '', apellido: '', nacionalidad: '', fechaNacimiento: '' };

  constructor(public api: ApiService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.api.getAutores().subscribe({
      next: (res: any) => this.autores = res,
      error: (err) => console.error('Error al cargar autores:', err)
    });
  }

  guardarAutor(): void {
    this.api.crearAutor(this.nuevoAutor).subscribe(() => {
      this.nuevoAutor = { nombre: '', apellido: '', nacionalidad: '', fechaNacimiento: '' };
      this.cargar();
    });
  }

  eliminar(id: number): void {
    if (confirm('¿Deseas eliminar este autor?')) {
      this.api.eliminarAutor(id).subscribe({
        next: () => this.cargar(),
        error: () => alert('Error: No se puede eliminar el autor porque tiene libros registrados.')
      });
    }
  }
}