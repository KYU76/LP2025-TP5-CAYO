package dev.labintec.tramite.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción para señalar que un recurso ya existe.
 * Esta excepción devuelve HTTP 409 (Conflict) gracias a la anotación
 * @author Quique
 */
@ResponseStatus(HttpStatus.CONFLICT) // Asigna estado 409 al lanzar esta excepción
public class RecursoDuplicadoException extends RuntimeException {

    /**
     * Crea la excepción con un mensaje que describe la duplicidad detectada.
     * @param message texto que detalla el conflicto de recursos
     */
    public RecursoDuplicadoException(String message) {
        super(message);
    }
}