package dev.labintec.tramite.controlador;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import dev.labintec.tramite.excepcion.RecursoDuplicadoException;
import dev.labintec.tramite.excepcion.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Punto central de manejo de errores para los controladores REST.
 * Construye respuestas JSON uniformes ante excepciones específicas,
 * incluyendo marca temporal, código HTTP, descripción, detalle y ruta.
 * Excepciones gestionadas:
 *  - RecursoNoEncontradoException → HTTP 404 Not Found
 *  - RecursoDuplicadoException    → HTTP 409 Conflict
 * @author Quique
 */
@RestControllerAdvice // Intercepta excepciones en todos los @RestController
public class ManejadorExcepciones {
    
    /**
     * Construye la respuesta para ausencia de recurso.
     * @param e excepción que indica que el recurso no existe.
     * @param request objeto que aporta la URI de la petición.
     * @return ResponseEntity con cuerpo de error y estado 404.
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> maanejarRecursoNoEncontrado(RecursoNoEncontradoException e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap();
        error.put("timestamp", LocalDateTime.now());            // Hora del incidente
        error.put("status", HttpStatus.NOT_FOUND.value());      // Código HTTP 404
        error.put("error", "Recurso no encontrado");            // Descripción breve
        error.put("message", e.getMessage());                   // Detalle enviado por la excepción
        error.put("path", request.getRequestURI());             // Ruta invocada por el cliente
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Construye la respuesta para recurso duplicado.
     * @param e excepción que indica conflicto de recursos
     * @param request objeto que aporta la URI de la petición
     * @return ResponseEntity con cuerpo de error y estado 409
     */
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> maanejarRecursoDuplicado(RecursoDuplicadoException e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap();
        error.put("timestamp", LocalDateTime.now());            // Hora del incidente
        error.put("status", HttpStatus.CONFLICT.value());       // Código HTTP 409
        error.put("error", "Recurso duplicado");                // Descripción breve
        error.put("message", e.getMessage());                   // Detalle enviado por la excepción
        error.put("path", request.getRequestURI());             // Ruta invocada por el cliente
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}