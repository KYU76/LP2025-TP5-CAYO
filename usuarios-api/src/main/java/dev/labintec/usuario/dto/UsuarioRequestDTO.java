package dev.labintec.usuario.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Datos recibidos en las peticiones de creación o actualización de usuario.
 * Incluye el nombre de usuario y la contraseña en texto plano.
 * @author Quique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    /**
     * Nombre único que el usuario proporciona para su cuenta.
     */
    @NotBlank(message = "En nombre de usuario es obligatorio")
    private String username;

    /**
     * Contraseña en texto plano para registro o cambio de credenciales.
     */
    @NotBlank(message = "En password es obligatorio")
    private String password;
}