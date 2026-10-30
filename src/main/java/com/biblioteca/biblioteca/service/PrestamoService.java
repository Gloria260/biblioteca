/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
}
