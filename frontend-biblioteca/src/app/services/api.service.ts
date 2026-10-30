import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private url = 'http://localhost:8080/api';
  public usuario = { id: 2, rol: 'LECTOR' };

  constructor(private http: HttpClient) {}

  // --- RUTAS DE LIBROS ---
  getLibros(buscar: string = '', pagina: number = 0): Observable<any> {
    return this.http.get(`${this.url}/libros?buscar=${buscar}&page=${pagina}&size=5`);
  }
  crearLibro(libro: any): Observable<any> {
    return this.http.post(`${this.url}/libros`, libro);
  }
  actualizarLibro(id: number, libro: any): Observable<any> {
    return this.http.put(`${this.url}/libros/${id}`, libro);
  }
  eliminarLibro(id: number): Observable<any> {
    return this.http.delete(`${this.url}/libros/${id}`);
  }

  // --- RUTAS DE AUTORES ---
  getAutores(): Observable<any> {
    return this.http.get(`${this.url}/autores`);
  }
  crearAutor(autor: any): Observable<any> {
    return this.http.post(`${this.url}/autores`, autor);
  }
  eliminarAutor(id: number): Observable<any> {
    return this.http.delete(`${this.url}/autores/${id}`);
  }

  // --- RUTAS DE PRESTAMOS ---
  guardarPrestamo(datos: any): Observable<any> {
    return this.http.post(`${this.url}/prestamos`, datos);
  }
  getPrestamos(): Observable<any> {
    return this.http.get(`${this.url}/prestamos`);
  }

// --- RUTAS DE USUARIOS Y DEVOLUCIONES ---
  getUsuarios(): Observable<any> {
    return this.http.get(`${this.url}/usuarios`);
  }

  devolverPrestamo(id: number): Observable<any> {
    return this.http.put(`${this.url}/prestamos/${id}/devolver`, {});
  }
eliminarUsuario(id: number): Observable<any> {
    return this.http.delete(`${this.url}/usuarios/${id}`);
  }
crearUsuario(usuario: any): Observable<any> {
    return this.http.post(`${this.url}/usuarios`, usuario);
  }

  actualizarUsuario(id: number, usuario: any): Observable<any> {
    return this.http.put(`${this.url}/usuarios/${id}`, usuario);
  }
}