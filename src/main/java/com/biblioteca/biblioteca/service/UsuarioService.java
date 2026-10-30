<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.AuthDTO;
import com.biblioteca.biblioteca.model.Usuario;
<<<<<<< HEAD
import com.biblioteca.biblioteca.repository.PrestamoRepository; // Importante para la validacion
=======
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
<<<<<<< HEAD
    private final PrestamoRepository prestamoRepository; // Necesario para revisar si tiene libros

    public UsuarioService(UsuarioRepository usuarioRepository, PrestamoRepository prestamoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.prestamoRepository = prestamoRepository;
=======

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
<<<<<<< HEAD
        // Al crear un usuario nuevo, por defecto le ponemos estado ACTIVO si no viene
        if (usuario.getEstado() == null) {
            usuario.setEstado(Usuario.Estado.ACTIVO); // Asumiendo que usas el enum Estado en tu modelo
        }
        return usuarioRepository.save(usuario);
    }
    
    // NUEVO: Método para actualizar un usuario existente
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuarioActualizado.getNombre());
            u.setUsername(usuarioActualizado.getUsername());
            u.setEmail(usuarioActualizado.getEmail());
            u.setRol(usuarioActualizado.getRol());
            // No actualizamos el password aquí por seguridad en este nivel
            return usuarioRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // NUEVO: El método eliminar con la regla de negocio
    public void eliminarUsuario(Long id) {
    // Buscamos los préstamos de este usuario y revisamos si alguno tiene devuelto == false
   boolean tienePrestamosActivos = prestamoRepository.findByUsuarioId(id)
        .stream()
        .anyMatch(p -> !p.getDevuelto());
    
    if (tienePrestamosActivos) {
        throw new RuntimeException("No se puede eliminar el usuario. Tiene préstamos activos sin devolver.");
    }
    
    usuarioRepository.deleteById(id);
}
=======
        return usuarioRepository.save(usuario);
    }
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3

    public Optional<Usuario> loginBasico(AuthDTO dto) {
        Optional<Usuario> optUser = usuarioRepository.findByUsername(dto.getUsername());
        if (optUser.isPresent()) {
            Usuario u = optUser.get();
<<<<<<< HEAD
=======
            // Verificación simple de contraseña mientras integras JWT el sábado
>>>>>>> 21ab82ae55f2408850569d14f74126f4a70536a3
            if (u.getPassword().equals(dto.getPassword()) && u.getEstado() == Usuario.Estado.ACTIVO) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}