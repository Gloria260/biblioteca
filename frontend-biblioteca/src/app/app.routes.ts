import { Routes } from '@angular/router';
import { Libros } from './Libros/Libros';
import { Autores } from './Autores/Autores';
import { Prestamos } from './Prestamos/Prestamos';
import { Usuarios } from './Usuarios/Usuarios'; // Apunta correctamente a la carpeta Usuarios

export const routes: Routes = [
  { path: '', redirectTo: 'libros', pathMatch: 'full' }, 
  { path: 'libros', component: Libros },
  { path: 'autores', component: Autores },
  { path: 'prestamos', component: Prestamos },
  { path: 'usuarios', component: Usuarios }
];