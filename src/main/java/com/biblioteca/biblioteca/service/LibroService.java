package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.LibroDTO;
import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.model.Libro;
import com.biblioteca.biblioteca.repository.AutorRepository;
import com.biblioteca.biblioteca.repository.LibroRepository;
import com.biblioteca.biblioteca.repository.PrestamoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final PrestamoRepository prestamoRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository, PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public Page<Libro> listarPaginado(String texto, Pageable pageable) {
        if (texto != null && !texto.trim().isEmpty()) {
            return libroRepository.findByTituloContainingIgnoreCaseOrAutorNombreContainingIgnoreCase(texto, texto, pageable);
        }
        return libroRepository.findAll(pageable);
    }

    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro crearLibro(LibroDTO dto) {
        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + dto.getAutorId()));

        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setIsbn(dto.getIsbn());
        libro.setAnio(dto.getAnio());
        libro.setAutor(autor);

        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, LibroDTO dto) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + dto.getAutorId()));

        libro.setTitulo(dto.getTitulo());
        libro.setIsbn(dto.getIsbn());
        libro.setAnio(dto.getAnio());
        libro.setAutor(autor);

        return libroRepository.save(libro);
    }

    public void eliminar(Long id) {
        // Regla de Negocio: Evitar borrar un libro si tiene un préstamo activo
        boolean estaPrestado = prestamoRepository.existsByLibroIdAndDevuelto(id, false);
        if (estaPrestado) {
            throw new RuntimeException("No se puede eliminar el libro porque tiene un préstamo activo.");
        }
        libroRepository.deleteById(id);
    }
}