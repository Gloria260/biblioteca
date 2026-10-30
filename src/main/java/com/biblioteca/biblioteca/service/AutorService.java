<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.repository.AutorRepository;
<<<<<<< HEAD
import com.biblioteca.biblioteca.repository.LibroRepository;
=======
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
<<<<<<< HEAD
    private final LibroRepository libroRepository;

    public AutorService(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
=======

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
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
<<<<<<< HEAD
        // Regla de Negocio: Evitar borrar un autor si ya tiene libros registrados
        boolean tieneLibros = libroRepository.existsByAutorId(id);
        if (tieneLibros) {
            throw new RuntimeException("No se puede eliminar el autor porque tiene libros registrados en el catálogo.");
        }
=======
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
        autorRepository.deleteById(id);
    }
}