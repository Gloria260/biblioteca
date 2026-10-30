import { Routes } from '@angular/router';
import { Libros } from './Libros/Libros';
<<<<<<< HEAD
import { Autores } from './Autores/Autores';
import { Prestamos } from './Prestamos/Prestamos';
import { Usuarios } from './Usuarios/Usuarios'; // Apunta correctamente a la carpeta Usuarios

export const routes: Routes = [
  { path: '', redirectTo: 'libros', pathMatch: 'full' }, 
  { path: 'libros', component: Libros },
  { path: 'autores', component: Autores },
  { path: 'prestamos', component: Prestamos },
  { path: 'usuarios', component: Usuarios }
=======

export const routes: Routes = [
  { path: '', component: Libros }, // Carga Libros automáticamente al abrir http://localhost:4200
  { path: '**', redirectTo: '' }
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
];