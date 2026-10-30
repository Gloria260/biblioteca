// Indica el paquete principal de Java donde se encuentra organizada la clase
package com.biblioteca.biblioteca;

// Importa la clase SpringApplication, encargada de iniciar y arrancar el servidor embebido (Tomcat)
import org.springframework.boot.SpringApplication;

// Importa la anotación principal de Spring Boot para la auto-configuración del proyecto
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Anotación principal que activa 3 funcionalidades clave de Spring Boot:
 * 1. @Configuration: Permite registrar componentes y beans dentro del contexto de Spring.
 * 2. @EnableAutoConfiguration: Configura automáticamente la aplicación según las dependencias (JPA, MySQL, Web).
 * 3. @ComponentScan: Escanea este paquete y sus subcarpetas en busca de Controllers, Services y Repositories.
 */
@SpringBootApplication
public class BibliotecaApplication {

	/**
	 * Método principal (main) de ejecución del programa en Java.
	 * Es el punto de entrada oficial por donde arranca toda la aplicación del Backend.
	 */
	public static void main(String[] args) {
		// Arranca la aplicación de Spring Boot, inicia el servidor en el puerto 8080 y conecta los servicios
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
