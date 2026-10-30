package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.PrestamoDTO;
import com.biblioteca.biblioteca.model.Prestamo;
import com.biblioteca.biblioteca.service.PrestamoService;
import org.springframework.web.bind.annotation.*;
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
