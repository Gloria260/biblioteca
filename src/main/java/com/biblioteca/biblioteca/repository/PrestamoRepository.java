<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByUsuarioId(Long usuarioId);

    Page<Prestamo> findByUsuarioUsernameContainingIgnoreCaseOrLibroTituloContainingIgnoreCase(
            String username, String titulo, Pageable pageable
    );
<<<<<<< HEAD

    // Método para validar la regla de negocio
    boolean existsByLibroIdAndDevuelto(Long libroId, boolean devuelto);
=======
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
}