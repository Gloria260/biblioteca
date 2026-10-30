import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './Usuarios.html' // Apunta correctamente al HTML que sí existe en la carpeta
})
export class Usuarios implements OnInit {
  usuariosConEstadisticas: any[] = [];
  mensajeError: string = '';
  
  nuevoUsuario: any = { id: null, nombre: '', username: '', email: '', password: '', rol: 'LECTOR' };
  modoEdicion: boolean = false;

  constructor(public api: ApiService) {}

  ngOnInit(): void {
    this.cargarDatosCombinados();
  }

  cargarDatosCombinados(): void {
    this.api.getUsuarios().subscribe({
      next: (resUsuarios: any) => {
        let listaUsuarios = Array.isArray(resUsuarios) ? resUsuarios : (resUsuarios.content || []);
        
        this.api.getPrestamos().subscribe({
          next: (resPrestamos: any) => {
            let listaPrestamos = Array.isArray(resPrestamos) ? resPrestamos : (resPrestamos.content || []);

            this.usuariosConEstadisticas = listaUsuarios.map((u: any) => {
              let susPrestamos = listaPrestamos.filter((p: any) => p.usuario?.id === u.id);
              return { 
                ...u, 
                activos: susPrestamos.filter((p: any) => p.devuelto === false).length, 
                devueltos: susPrestamos.filter((p: any) => p.devuelto === true).length 
              };
            });
          }
        });
      },
      error: (err) => console.error('Error cargando datos', err)
    });
  }

  guardarUsuario(): void {
    if(!this.nuevoUsuario.nombre || !this.nuevoUsuario.username || !this.nuevoUsuario.email) {
      this.mensajeError = "Nombre, Username y Email son obligatorios.";
      return;
    }

    if (this.modoEdicion) {
      this.api.actualizarUsuario(this.nuevoUsuario.id, this.nuevoUsuario).subscribe({
        next: () => {
          this.limpiarFormulario();
          this.cargarDatosCombinados();
        },
        error: (err) => this.mensajeError = 'Error al actualizar usuario.'
      });
    } else {
      if(!this.nuevoUsuario.password) {
         this.mensajeError = "La contraseña es obligatoria para un nuevo usuario.";
         return;
      }
      
      const usuarioAEnviar = { ...this.nuevoUsuario, password_hash: this.nuevoUsuario.password }; 
      
      this.api.crearUsuario(usuarioAEnviar).subscribe({
        next: () => {
          this.limpiarFormulario();
          this.cargarDatosCombinados();
        },
        error: (err) => this.mensajeError = 'Error al crear usuario.'
      });
    }
  }

  editar(usuario: any): void {
    this.modoEdicion = true;
    this.nuevoUsuario = { 
      id: usuario.id, 
      nombre: usuario.nombre, 
      username: usuario.username, 
      email: usuario.email, 
      rol: usuario.rol 
    };
    this.mensajeError = '';
  }

  limpiarFormulario(): void {
    this.modoEdicion = false;
    this.nuevoUsuario = { id: null, nombre: '', username: '', email: '', password: '', rol: 'LECTOR' };
    this.mensajeError = '';
  }

  eliminar(usuario: any): void {
    if (usuario.activos > 0) {
      this.mensajeError = `El usuario ${usuario.username} tiene ${usuario.activos} libro(s) sin devolver.`;
      return;
    }

    if (confirm(`¿Eliminar al usuario ${usuario.username}?`)) {
      this.api.eliminarUsuario(usuario.id).subscribe({
        next: () => {
          this.mensajeError = '';
          this.cargarDatosCombinados();
        },
        error: (err) => this.mensajeError = err.error?.message || 'Error al eliminar.'
      });
    }
  }
}