<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.PrestamoDTO;
import com.biblioteca.biblioteca.model.Prestamo;
import com.biblioteca.biblioteca.service.PrestamoService;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> listarPorUsuario(@PathVariable Long usuarioId) {
        return prestamoService.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public Prestamo guardar(@RequestBody PrestamoDTO dto) {
        return prestamoService.registrarPrestamo(dto);
    }

    @PutMapping("/{id}/devolver")
    public Prestamo devolver(@PathVariable Long id) {
        return prestamoService.marcarComoDevuelto(id);
    }
}
