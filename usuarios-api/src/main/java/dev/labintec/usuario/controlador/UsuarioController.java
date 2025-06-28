package dev.labintec.usuario.controlador;

import dev.labintec.usuario.dto.UsuarioMapper;
import dev.labintec.usuario.dto.UsuarioRequestDTO;
import dev.labintec.usuario.dto.UsuarioResponseDTO;
import dev.labintec.usuario.entidad.Usuario;
import dev.labintec.usuario.servicio.UsuarioService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de usuarios.
 * Expone endpoints que aceptan {@link UsuarioRequestDTO} y devuelven
 * UsuarioResponseDTO, garantizando validación y mapeo claro entre 
 * la capa de negocio y la API.
 *
 * Endpoints:
 *  - GET /api/v1/usuarios                      → lista todos los usuarios
 *  - GET /api/v1/usuarios/{id}                 → obtiene usuario por ID
 *  - GET /api/v1/usuarios/username/{username}  → obtiene por nombre de usuario
 *  - POST /api/v1/usuarios                     → crea un nuevo usuario
 *  - PUT /api/v1/usuarios/{username}           → actualiza usuario existente
 *  - DELETE /api/v1/usuarios/{username}        → elimina usuario existente
 * @author Quique
 */
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    /**
     * Lista todos los usuarios registrados.
     * @return colección de UsuarioResponseDTO.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = servicio.listarTodos();
        List<UsuarioResponseDTO> rdto = new ArrayList<>();
        for (Usuario usuario : usuarios)
            rdto.add(UsuarioMapper.toDTO(usuario));
        return rdto;
    }

    /**
     * Recupera un usuario por su identificador.
     * @param id clave primaria del usuario.
     * @return UsuarioResponseDTO asociado al ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO obtenerPorId(@PathVariable Long id) {
        Usuario usuario = servicio.obtenerPorId(id);     // Obtiene entidad o lanza 404
        return UsuarioMapper.toDTO(usuario);             // Mapea a DTO de salida
    }

    /**
     * Recupera un usuario por su nombre único.
     * @param username cadena que identifica al usuario.
     * @return UsuarioResponseDTO asociado al username.
     */
    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO obtenerPorUsername(@PathVariable String username) {
        Usuario usuario = servicio.obtenerPorUsername(username);
        return UsuarioMapper.toDTO(usuario);
    }

    /**
     * Crea un usuario nuevo a partir de datos validados.
     * @param dto datos de creación en UsuarioRequestDTO.
     * @return UsuarioResponseDTO con el usuario persistido.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO crearUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario entidad = UsuarioMapper.toUsuario(dto);   // Convierte RequestDTO → Entidad
        Usuario guardado = servicio.crear(entidad);       // Aplica validaciones y guarda
        return UsuarioMapper.toDTO(guardado);             // Convierte Entidad → ResponseDTO
    }

    /**
     * Actualiza un usuario existente identificado por username.
     * @param username clave única del usuario a modificar.
     * @param dto datos nuevos en UsuarioRequestDTO.
     * @return UsuarioResponseDTO con los cambios aplicados.
     */
    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO actualizarPassword(@PathVariable String username, @Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario actualizado = servicio.actualizarPassword(username, UsuarioMapper.toUsuario(dto));
        return UsuarioMapper.toDTO(actualizado);
    }

    /**
     * Elimina un usuario existente por su nombre único.
     * @param username clave única del usuario a borrar
     */
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable String username) {
        servicio.eliminar(username);    // Lanza 404 si no existe
    }
}