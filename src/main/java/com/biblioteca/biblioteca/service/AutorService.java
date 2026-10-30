package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.repository.AutorRepository;
import com.biblioteca.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    public AutorService(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    public void eliminar(Long id) {
        // Regla de Negocio: Evitar borrar un autor si ya tiene libros registrados
        boolean tieneLibros = libroRepository.existsByAutorId(id);
        if (tieneLibros) {
            throw new RuntimeException("No se puede eliminar el autor porque tiene libros registrados en el catálogo.");
        }
        autorRepository.deleteById(id);
    }
}