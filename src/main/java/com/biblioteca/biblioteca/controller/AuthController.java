/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Paquete donde se encuentra ubicado el controlador dentro del proyecto
package com.biblioteca.biblioteca.controller;

// Importaciones de clases DTO, modelos y servicios necesarios para la autenticación
import com.biblioteca.biblioteca.dto.AuthDTO;
import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.service.UsuarioService;

// Importaciones de Spring Web para la creación de la API REST
import org.springframework.web.bind.annotation.*;

// Importación de Java para manejar valores opcionales y evitar errores de 'NullPointerException'
import java.util.Optional;

/**
 * @RestController: Le indica a Spring Boot que esta clase es un Controlador REST.
 * Significa que recibirá peticiones HTTP y devolverá respuestas directamente en formato JSON.
 */
@RestController

/**
 * @RequestMapping("/api/auth"): Define la ruta base para los endpoints de este controlador.
 * Todas las peticiones a este archivo comenzarán por: http://localhost:8080/api/auth
 */
@RequestMapping("/api/auth")

/**
 * @CrossOrigin(origins = "*"): Permite la comunicación entre el puerto del Frontend (Angular en :4200)
 * y el Backend (Spring Boot en :8080), evitando el bloqueo por políticas de seguridad del navegador (CORS).
 */
@CrossOrigin(origins = "*")
public class AuthController {

    // Declaración del servicio de usuarios (Inyección de dependencias)
    private final UsuarioService usuarioService;

    /**
     * Constructor para la Inyección de Dependencias.
     * Spring Boot pasa automáticamente la instancia de 'UsuarioService' al crear el controlador.
     */
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * @PostMapping("/login"): Define un endpoint de tipo POST en la URL '/api/auth/login'.
     * Sirve para procesar los datos de inicio de sesión enviados desde el formulario de Angular.
     * 
     * @RequestBody AuthDTO dto: Captura el cuerpo del JSON enviado por Angular (correo/usuario y contraseña)
     * y lo convierte automáticamente en un objeto de tipo 'AuthDTO'.
     * 
     * @return Retorna el objeto 'Usuario' si las credenciales son válidas.
     */
    @PostMapping("/login")
    public Usuario login(@RequestBody AuthDTO dto) {
        // Llama al servicio para validar si el usuario y contraseña coinciden en la base de datos MySQL
        Optional<Usuario> user = usuarioService.loginBasico(dto);

        // Si se encontró el usuario en la base de datos (credenciales correctas)
        if (user.isPresent()) {
            return user.get(); // Retorna la información del usuario en formato JSON al frontend
        }

        // Si los datos son incorrectos, interrumpe el flujo y lanza una excepción de error HTTP
        throw new RuntimeException("Usuario o contraseña incorrectos");
    }
}