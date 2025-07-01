package dev.labintec.usuario.servicio;

import dev.labintec.usuario.entidad.Usuario;
import dev.labintec.usuario.excepcion.RecursoDuplicadoException;
import dev.labintec.usuario.excepcion.RecursoNoEncontradoException;
import dev.labintec.usuario.repositorio.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que agrupa las operaciones CRUD para la entidad Usuario.
 * Ofrece métodos para listar, obtener, crear, actualizar y eliminar usuarios
 * con validación de existencia y unicidad.
 * @author Quique
 */
@Service
public class UsuarioService {
    @Autowired // Inyección del repositorio JPA que gestiona la persistencia de Usuario
    private UsuarioRepository repositorio;

    /**
     * Recupera todos los usuarios registrados en la base de datos.
     * @return lista de todos los usuarios.
     */
    public List<Usuario> listarTodos() {
        return repositorio.findAll(); // Ejecuta SELECT * FROM user
    }

    /**
     * Obtiene un usuario según su identificador.
     * @param id clave primaria del usuario.
     * @return el Usuario correspondiente al ID.
     * @throws RecursoNoEncontradoException si no existe un usuario con ese ID.
     */
    public Usuario obtenerPorId(Long id) {
        Optional<Usuario> opcional = repositorio.findById(id);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Usuario con id = " + id + ", no existe.");
        return opcional.get();
    }

    /**
     * Obtiene un usuario por su nombre de usuario único.
     * @param username nombre único del usuario.
     * @return el Usuario asociado al nombre.
     * @throws RecursoNoEncontradoException si no hay coincidencias.
     */
    public Usuario obtenerPorUsername(String username) {
        Optional<Usuario> opcional = repositorio.findByUsername(username);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Usuario con nombre = " + username + ", no existe.");
        return opcional.get();
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     * Verifica previamente que no exista otro usuario con el mismo username.
     * @param usuario objeto con los datos del nuevo usuario.
     * @return el Usuario persistido con su ID generado.
     * @throws RecursoDuplicadoException si ya existe un username idéntico.
     */
    public Usuario crearUsuario(Usuario usuario) {
        Optional<Usuario> opcional = repositorio.findByUsername(usuario.getUsername());
        if (opcional.isPresent())
            throw new RecursoDuplicadoException("Usuario con nombre = " + usuario.getUsername() + ", ya existe.");
        return repositorio.save(usuario); // Ejecuta INSERT en la tabla user
    }

    /**
     * Actualiza únicamente la contraseña de un usuario existente.
     * Busca el usuario por su username, asigna la nueva contraseña
     * y persiste los cambios.
     * @param username clave única que identifica al usuario a modificar
     * @param usuarioActualizado objeto que aporta la nueva contraseña
     * @return el Usuario con la contraseña actualizada
     * @throws RecursoNoEncontradoException si no existe un usuario con ese username
     */
    public Usuario actualizarPassword(String username, Usuario usuarioActualizado) {
        // Busca el usuario existente por su username
        Optional<Usuario> opcional = repositorio.findByUsername(username);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Usuario con nombre = " + username + ", no existe.");
        Usuario usuario = opcional.get();
        /** 
        // Verifica duplicidad si se desea cambiar el username
        if (!usuario.getUsername().equals(usuarioActualizado.getUsername())) {
            Optional<Usuario> opcionalNuevo = repositorio.findByUsername(usuarioActualizado.getUsername());
            if (opcionalNuevo.isPresent() && usuarioActualizado.getUsername().equals(username))
                throw new RecursoDuplicadoException("Usuario con nombre = " + usuarioActualizado.getUsername() + ", ya existe.");
        }*/
        // Aplica los cambios permitidos (por ahora solo la contraseña)
        //usuario.setUsername(usuarioActualizado.getUsername());
        usuario.setPassword(usuarioActualizado.getPassword());
        // Persiste los cambios y devuelve el resultado
        return repositorio.save(usuario); // Ejecuta UPDATE en la tabla user
    }

    /**
     * Elimina un usuario definido por su username.
     * @param username nombre único del usuario a borrar.
     * @throws RecursoNoEncontradoException si no se encuentra el registro.
     */
    public void eliminarUsuario(String username) {
        Optional<Usuario> opcional = repositorio.findByUsername(username);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Usuario con nombre = " + username + ", no existe.");
        repositorio.delete(opcional.get()); // Ejecuta DELETE en la tabla user
    }
}