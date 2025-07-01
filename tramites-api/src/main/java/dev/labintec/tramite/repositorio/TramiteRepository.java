package dev.labintec.tramite.repositorio;

import dev.labintec.tramite.entidad.Tramite;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Traminte.
 * Esta interfaz extiende de JpaRepository, lo que le proporciona métodos CRUD
 * básicos, además de la posibilidad de definir consultas personalizadas.
 * @author Quique
 */
public interface TramiteRepository extends JpaRepository<Tramite, Long> {
    Optional<Tramite> findByType(String type);
}