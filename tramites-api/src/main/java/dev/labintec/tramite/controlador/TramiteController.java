package dev.labintec.tramite.controlador;

import dev.labintec.tramite.dto.TramiteMapper;
import dev.labintec.tramite.dto.TramiteRequestDTO;
import dev.labintec.tramite.dto.TramiteResponseDTO;
import dev.labintec.tramite.entidad.Tramite;
import dev.labintec.tramite.servicio.TramiteService;
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
 * Controlador REST para la gestión de trámites.
 * Expone endpoints que aceptan TramiteRequestDTO y devuelven
 * TramiteResponseDTO, garantizando validación de entrada y mapeo
 * entre la API y la lógica de negocio.
 * 
 * Endpoints:
 *  - GET /api/v1/tramites                 → lista todos los trámites
 *  - GET /api/v1/tramites/{id}            → obtiene trámite por ID
 *  - GET /api/v1/tramites/type/{type}     → obtiene trámite por type
 *  - POST /api/v1/tramites                → crea un nuevo trámite
 *  - PUT /api/v1/tramites/{id}            → actualiza un trámite existente
 *  - DELETE /api/v1/tramites/{id}         → elimina un trámite por ID
 * @author Quique
 */
@RestController
@RequestMapping("api/v1/tramites")
public class TramiteController {

    @Autowired
    private TramiteService servicio;

    /**
     * Lista todos los trámites registrados.
     * @return colección de TramiteResponseDTO.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TramiteResponseDTO> listarTramites() {
        List<Tramite> tramites = servicio.listarTodos();
        List<TramiteResponseDTO> rdto = new ArrayList<>();
        for (Tramite tramite : tramites)
            rdto.add(TramiteMapper.toDTO(tramite));
        return rdto;
    }

    /**
     * Recupera un trámite por su identificador.
     * @param id clave primaria del trámite.
     * @return TramiteResponseDTO asociado al ID.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TramiteResponseDTO obtenerPorId(@PathVariable Long id) {
        Tramite tramite = servicio.obtenerPorId(id);
        return TramiteMapper.toDTO(tramite);
    }

    /**
     * Recupera un trámite por su type único.
     * @param type cadena que identifica al trámite.
     * @return TramiteResponseDTO asociado al type.
     */
    @GetMapping("/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public TramiteResponseDTO obtenerPorType(@PathVariable String type) {
        Tramite tramite = servicio.obtenerPorType(type);
        return TramiteMapper.toDTO(tramite);
    }

    /**
     * Crea un trámite nuevo a partir de datos validados.
     * @param dto datos de creación en TramiteRequestDTO.
     * @return TramiteResponseDTO con el tramite persistido.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TramiteResponseDTO crearTramite(@Valid @RequestBody TramiteRequestDTO dto) {
        Tramite entidad = TramiteMapper.toTramite(dto);
        Tramite guardado = servicio.crearTramite(entidad);
        return TramiteMapper.toDTO(guardado);
    }

    /**
     * Actualiza un trámite existente identificado por type.
     * @param type tipo único del trámite a modificar.
     * @param dto datos nuevos en TramiteRequestDTO.
     * @return TramiteResponseDTO con los cambios aplicados.
     */
    @PutMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public TramiteResponseDTO actualizarStatus(@PathVariable String type, @Valid @RequestBody TramiteRequestDTO dto) {
        Tramite actualizado = servicio.actualizarStatus(type, TramiteMapper.toTramite(dto));
        return TramiteMapper.toDTO(actualizado);
    }

    /**
     * Elimina un trámite existente por su nombre único.
     * @param type tipo único del trámite a borrar
     */
    @DeleteMapping("/{type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTramite(@PathVariable String type) {
        servicio.eleminarTramite(type);
    }
}