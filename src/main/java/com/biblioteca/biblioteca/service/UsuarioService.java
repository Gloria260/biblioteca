/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.AuthDTO;
import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> loginBasico(AuthDTO dto) {
        Optional<Usuario> optUser = usuarioRepository.findByUsername(dto.getUsername());
        if (optUser.isPresent()) {
            Usuario u = optUser.get();
            // Verificación simple de contraseña mientras integras JWT el sábado
            if (u.getPassword().equals(dto.getPassword()) && u.getEstado() == Usuario.Estado.ACTIVO) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}