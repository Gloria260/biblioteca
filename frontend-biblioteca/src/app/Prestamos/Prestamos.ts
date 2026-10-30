import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-prestamos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './Prestamos.html'
})
export class Prestamos implements OnInit {
  prestamos: any[] = [];
  libros: any[] = [];
  usuarios: any[] = [];
  
  nuevoPrestamo = { libroId: null, usuarioId: null, fechaDevolucion: '' };
  mensaje: string = '';
  error: string = '';

  constructor(public api: ApiService) {}

  ngOnInit(): void {
    this.cargarPrestamos();
    this.cargarLibros();
    this.cargarUsuarios();
  }

  cargarPrestamos(): void {
    this.api.getPrestamos().subscribe({
      next: (res: any) => this.prestamos = Array.isArray(res) ? res : (res.content || [])
    });
  }

  cargarLibros(): void {
    this.api.getLibros('', 0).subscribe({
      next: (res: any) => this.libros = Array.isArray(res) ? res : (res.content || [])
    });
  }

  cargarUsuarios(): void {
    this.api.getUsuarios().subscribe({
      next: (res: any) => this.usuarios = Array.isArray(res) ? res : (res.content || [])
    });
  }

  registrarPrestamo(): void {
    if (!this.nuevoPrestamo.libroId || !this.nuevoPrestamo.usuarioId || !this.nuevoPrestamo.fechaDevolucion) {
      this.error = 'Por favor seleccione Libro, Usuario y Fecha.';
      this.mensaje = '';
      return;
    }

    const body = {
      libroId: this.nuevoPrestamo.libroId,
      usuarioId: this.nuevoPrestamo.usuarioId,
      fechaPrestamo: new Date().toISOString().split('T')[0], // Fecha de hoy automática
      fechaDevolucion: this.nuevoPrestamo.fechaDevolucion
    };

    this.api.guardarPrestamo(body).subscribe({
      next: () => {
        this.mensaje = '¡Préstamo registrado con éxito!';
        this.error = '';
        this.nuevoPrestamo = { libroId: null, usuarioId: null, fechaDevolucion: '' };
        this.cargarPrestamos(); // Actualiza la tabla
      },
      error: (err) => {
        // Muestra el error exacto que envía Spring Boot (ej: "El límite es 15 días")
        this.error = err.error?.message || 'Error de validación al registrar el préstamo.';
        this.mensaje = '';
      }
    });
  }

  devolverLibro(id: number): void {
    if (confirm('¿Confirmar que el usuario devolvió este libro?')) {
      this.api.devolverPrestamo(id).subscribe({
        next: () => this.cargarPrestamos(), // Recarga la tabla para mostrarlo en verde
        error: () => alert('Error al procesar la devolución.')
      });
    }
  }
}