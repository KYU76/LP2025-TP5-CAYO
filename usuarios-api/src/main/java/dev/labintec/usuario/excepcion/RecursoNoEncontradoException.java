package dev.labintec.usuario.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para indicar que un recurso no existe.
 * Esta excepción devuelve HTTP 404 (Not Found) gracias a la anotación.
 * @author Quique
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Código 404 automáticamente cuando se lanza esta excepción.
public class RecursoNoEncontradoException extends RuntimeException {

    /**
     * Crea la excepción con un mensaje que describe el recurso ausente.
     * @param message texto que explica la causa del error.
     */
    public RecursoNoEncontradoException(String message) {
        super(message);
    }
}