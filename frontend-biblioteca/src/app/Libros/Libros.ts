import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-libros',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './Libros.html'
})
export class Libros implements OnInit {
  libros: any[] = [];
<<<<<<< HEAD
  autores: any[] = []; // NUEVO: Arreglo para guardar los autores
=======
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
  seleccionado: any = null;
  filtro: string = '';
  pagina: number = 0;
  totalPaginas: number = 0;
  fechaDevolucion: string = '';
  mensaje: string = '';

<<<<<<< HEAD
  // Formulario para Crear / Editar (autorId inicia en null)
  nuevoLibro: any = { id: null, titulo: '', isbn: '', anio: 2024, autorId: null };
=======
  // Formulario para Crear / Editar
  nuevoLibro: any = { id: null, titulo: '', isbn: '', anio: 2024, autorId: 1 };
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
  modoEdicion: boolean = false;

  constructor(public api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
<<<<<<< HEAD
    this.cargarAutores(); // Carga la lista de autores al entrar a la pantalla
  }

  // NUEVO: Método para traer los autores usando tu ApiService
  cargarAutores(): void {
    this.api.getAutores().subscribe({
      next: (res: any) => {
        this.autores = res;
      },
      error: (err) => console.error('Error al cargar autores', err)
    });
  }

  cargar(): void {
    this.api.getLibros(this.filtro, this.pagina).subscribe({
      next: (res: any) => {
        console.log('Respuesta recibida del backend:', res);
        if (Array.isArray(res)) {
          this.libros = res;
          this.totalPaginas = 1;
        } else if (res && res.content) {
          this.libros = res.content;
          this.totalPaginas = res.totalPages || 1;
        } else {
          this.libros = [];
        }
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al conectar con Spring Boot:', err);
      }
    });
  }
=======
  }

  cargar(): void {
  this.api.getLibros(this.filtro, this.pagina).subscribe({
    next: (res: any) => {
      console.log('Respuesta recibida del backend:', res);

      // Si la respuesta es una Lista directa [] o un objeto con .content
      if (Array.isArray(res)) {
        this.libros = res;
        this.totalPaginas = 1;
      } else if (res && res.content) {
        this.libros = res.content;
        this.totalPaginas = res.totalPages || 1;
      } else {
        this.libros = [];
      }

      // Forzar renderizado en pantalla
      this.cdr.detectChanges();
    },
    error: (err) => {
      console.error('Error al conectar con Spring Boot:', err);
    }
  });
}
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3

  buscar(): void {
    this.pagina = 0;
    this.cargar();
  }

  verDetalle(libro: any): void {
    this.seleccionado = libro;
    this.mensaje = '';
  }

  guardarLibro(): void {
    if (this.modoEdicion) {
      this.api.actualizarLibro(this.nuevoLibro.id, this.nuevoLibro).subscribe(() => {
        this.limpiarFormulario();
        this.cargar();
      });
    } else {
      this.api.crearLibro(this.nuevoLibro).subscribe(() => {
        this.limpiarFormulario();
        this.cargar();
      });
    }
  }

  editar(libro: any): void {
    this.modoEdicion = true;
<<<<<<< HEAD
    this.nuevoLibro = { ...libro, autorId: libro.autor?.id || null };
=======
    this.nuevoLibro = { ...libro, autorId: libro.autor?.id || 1 };
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
  }

  eliminar(id: number): void {
    if (confirm('¿Deseas eliminar este libro?')) {
      this.api.eliminarLibro(id).subscribe(() => this.cargar());
    }
  }

  limpiarFormulario(): void {
    this.modoEdicion = false;
<<<<<<< HEAD
    this.nuevoLibro = { id: null, titulo: '', isbn: '', anio: 2024, autorId: null };
=======
    this.nuevoLibro = { id: null, titulo: '', isbn: '', anio: 2024, autorId: 1 };
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
  }

  pedirPrestamo(): void {
    if (!this.fechaDevolucion) return;
    const body = {
      libroId: this.seleccionado.id,
      usuarioId: this.api.usuario.id,
      fechaPrestamo: new Date().toISOString().split('T')[0],
      fechaDevolucion: this.fechaDevolucion
    };
    this.api.guardarPrestamo(body).subscribe(() => {
      this.mensaje = '¡Préstamo realizado con éxito!';
      this.fechaDevolucion = '';
    });
  }
}