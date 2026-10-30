/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
<<<<<<< HEAD
    
    Page<Libro> findByTituloContainingIgnoreCaseOrAutorNombreContainingIgnoreCase(String titulo, String autorNombre, Pageable pageable);
    
    // Método para la regla de negocio: validar si un autor tiene libros
    boolean existsByAutorId(Long autorId);
}
=======

    // Búsqueda paginada por título o nombre del autor
    Page<Libro> findByTituloContainingIgnoreCaseOrAutorNombreContainingIgnoreCase(
            String titulo, String autorNombre, Pageable pageable
    );
}
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
