package dev.labintec.usuario.dto;

import dev.labintec.usuario.entidad.Usuario;

/**
 * Conversor entre la entidad {@link Usuario} y los objetos DTO de usuario.
 * Facilita la transformación de la capa de persistencia a la API y viceversa.
 * @author Quique
 */
public class UsuarioMapper {

    /**
     * Crea un DTO de respuesta a partir de la entidad {@link Usuario}.
     * @param usuario entidad con datos persistidos
     * @return instancia de {@link UsuarioResponseDTO} o null si usuario es null
     */
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null)
            return null;
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUser(usuario.getId_user());
        dto.setUsername(usuario.getUsername());
        return dto;
    }

    /**
     * Genera una entidad Usuario a partir de un DTO de petición.
     * @param dto objeto con datos proporcionados por el cliente
     * @return instancia de Usuario}o null si dto es null
     */
    public static Usuario toUsuario(UsuarioRequestDTO dto) {
        if (dto == null)
            return null;
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        return usuario;
    }
}