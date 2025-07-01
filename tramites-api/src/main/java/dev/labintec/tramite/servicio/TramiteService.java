package dev.labintec.tramite.servicio;

import dev.labintec.tramite.entidad.Tramite;
import dev.labintec.tramite.excepcion.RecursoDuplicadoException;
import dev.labintec.tramite.excepcion.RecursoNoEncontradoException;
import dev.labintec.tramite.repositorio.TramiteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Quique
 */
@Service
public class TramiteService {
    @Autowired
    private TramiteRepository repositorio;

    /**
     * Recupera todos los trámites registrados en la base de datos.
     * @return lista de todos los trámites.
     */
    public List<Tramite> listarTodos() {
        return repositorio.findAll(); // Ejecuta SELECT * FROM transaction
    }

    /**
     * Obtiene un trámite según su identificador.
     * @param id clave primaria del trámite.
     * @return el Tramite correspondiente al ID.
     * @throws RecursoNoEncontradoException si no existe un trámite con ese ID.
     */
    public Tramite obtenerPorId(Long id) {
        Optional<Tramite> opcional = repositorio.findById(id);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Trámite con id = " + id + ", no existe.");
        return opcional.get();
    }

    /**
     * Obtiene un trámite por su type de trámite único.
     * @param type nombre único del trámite.
     * @return el Trámite asociado al nombre.
     * @throws RecursoNoEncontradoException si no hay coincidencias.
     */
    public Tramite obtenerPorType(String type) {
        Optional<Tramite> opcional = repositorio.findByType(type);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Trámite de tipo = " + type + ", no existe.");
        return opcional.get();
    }

    /**
     * Registra un nuevo trámite en la base de datos.
     * Verifica previamente que no exista otro trámite del mismo type.
     * @param tramite objeto con los datos del nuevo trámite.
     * @return el Tramite persistido con su ID generado.
     * @throws RecursoDuplicadoException si ya existe un type idéntico.
     */
    public Tramite crearTramite(Tramite tramite) {
        Optional<Tramite> opcional = repositorio.findByType(tramite.getType());
        if (opcional.isPresent())
            throw new RecursoDuplicadoException("Trámite de tipo = " + tramite.getType() + ", ya existe.");
        return repositorio.save(tramite); // Ejecuta INSERT en la tabla transaction
    }

    /**
     * Actualiza únicamente el estado de un trámite existente.
     * Busca el trámite por su status, asigna el nuevo status
     * y persiste los cambios.
     * @param type clave única que identifica al trámite a modificar.
     * @param TramiteActualizado objeto que aporta el nuevo estado.
     * @return el Tramite con el estado actualizado.
     * @throws RecursoNoEncontradoException si no existe un trámite de ese type
     */
    public Tramite actualizarStatus(String type, Tramite TramiteActualizado) {
        Optional<Tramite> opcional = repositorio.findByType(type);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Trámite de tipo = " + type + ", no existe.");
        Tramite tramite = opcional.get();
        // Aplica los cambios permitidos
        //tramite.setType(TramiteActualizado.getType());
        tramite.setStatus(TramiteActualizado.getStatus());
        return repositorio.save(tramite); // Ejecuta UPDATE en la tabla transaction
    }

    public void eleminarTramite(String type) {
        Optional<Tramite> opcional = repositorio.findByType(type);
        if (!opcional.isPresent())
            throw new RecursoNoEncontradoException("Trámite de tipo = " + type + ", no existe.");
        repositorio.delete(opcional.get()); // Ejecuta DELETE en la tabla transaction
    }
}