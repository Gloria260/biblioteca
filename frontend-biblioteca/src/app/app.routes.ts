import { Routes } from '@angular/router';
import { Libros } from './Libros/Libros';

export const routes: Routes = [
  { path: '', component: Libros }, // Carga Libros automáticamente al abrir http://localhost:4200
  { path: '**', redirectTo: '' }
];