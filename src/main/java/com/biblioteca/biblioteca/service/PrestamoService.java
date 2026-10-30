<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.PrestamoDTO;
import com.biblioteca.biblioteca.model.Libro;
import com.biblioteca.biblioteca.model.Prestamo;
import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.repository.LibroRepository;
import com.biblioteca.biblioteca.repository.PrestamoRepository;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import java.time.temporal.ChronoUnit;
=======

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> listarPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public Page<Prestamo> listarPaginado(String texto, Pageable pageable) {
        if (texto != null && !texto.trim().isEmpty()) {
            return prestamoRepository.findByUsuarioUsernameContainingIgnoreCaseOrLibroTituloContainingIgnoreCase(texto, texto, pageable);
        }
        return prestamoRepository.findAll(pageable);
    }

    public Prestamo registrarPrestamo(PrestamoDTO dto) {
        Libro libro = libroRepository.findById(dto.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

<<<<<<< HEAD
        // NUEVA REGLA DE NEGOCIO: Validar fechas y límite de 15 días
        if (dto.getFechaPrestamo() != null && dto.getFechaDevolucion() != null) {
            long dias = ChronoUnit.DAYS.between(dto.getFechaPrestamo(), dto.getFechaDevolucion());
            if (dias < 0) {
                throw new RuntimeException("La fecha de devolución no puede ser anterior a la fecha de préstamo.");
            }
            if (dias > 15) {
                throw new RuntimeException("El límite máximo de préstamo es de 15 días.");
            }
        } else {
            throw new RuntimeException("Las fechas de préstamo y devolución son obligatorias.");
        }

        // Regla de Negocio original tuya: Validar que el libro no esté prestado actualmente
        boolean estaPrestado = prestamoRepository.existsByLibroIdAndDevuelto(dto.getLibroId(), false);
        if (estaPrestado) {
            throw new RuntimeException("El libro ya se encuentra prestado y no ha sido devuelto.");
        }

=======
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setDevuelto(false);

        return prestamoRepository.save(prestamo);
    }

    public Prestamo marcarComoDevuelto(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
        prestamo.setDevuelto(true);
        return prestamoRepository.save(prestamo);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
