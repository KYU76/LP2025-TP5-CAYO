package dev.labintec.usuario.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Datos de usuario devueltos en la respuesta de la API.
 * Contiene el identificador único y el nombre asociado.
 * @author Quique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    /**
     * Identificador asignado en la base de datos.
     */
    private Long idUser;

    /**
     * Nombre único que identifica al usuario.
     */
    @NotBlank(message = "En nombre de usuario es obligatorio")
    private String username;
}