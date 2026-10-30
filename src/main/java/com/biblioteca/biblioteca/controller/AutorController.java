/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.service.AutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "*")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> listar() {
        return autorService.listarTodos();
    }

    @PostMapping
    public Autor guardar(@RequestBody Autor autor) {
        return autorService.guardar(autor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        autorService.eliminar(id);
    }
}
