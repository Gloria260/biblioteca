/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.LibroDTO;
import com.biblioteca.biblioteca.model.Libro;
import com.biblioteca.biblioteca.service.LibroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public Page<Libro> listar(
            @RequestParam(defaultValue = "") String buscar,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return libroService.listarPaginado(buscar, PageRequest.of(page, size));
    }

    @PostMapping
    public Libro guardar(@RequestBody LibroDTO dto) {
        return libroService.crearLibro(dto);
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody LibroDTO dto) {
        return libroService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}
