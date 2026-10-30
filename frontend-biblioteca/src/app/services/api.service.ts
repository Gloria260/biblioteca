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

  guardarPrestamo(datos: any): Observable<any> {
    return this.http.post(`${this.url}/prestamos`, datos);
  }
}