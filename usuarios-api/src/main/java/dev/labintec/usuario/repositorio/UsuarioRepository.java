package dev.labintec.usuario.repositorio;

import dev.labintec.usuario.entidad.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Usuario.
 * Esta interfaz extiende de JpaRepository, lo que le proporciona métodos CRUD
 * básicos, además de la posibilidad de definir consultas personalizadas.
 * @author Quique
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}