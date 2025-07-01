package dev.labintec.tramite.dto;

import dev.labintec.tramite.entidad.Tramite;

/**
 * Conversor entre la entidad Tramite y los objetos DTO de trámite.
 * Facilita la transformación de la capa de persistencia a la API y viceversa.
 * @author Quique
 */
public class TramiteMapper {

    /**
     * Crea un DTO de respuesta a partir de la entidad Tramite.
     * @param tramite entidad con datos persistidos
     * @return instancia de TramiteResponseDTO o null si tramite es null
     */
    public static TramiteResponseDTO toDTO(Tramite tramite) {
        if (tramite == null)
            return null;
        TramiteResponseDTO dto = new TramiteResponseDTO();
        dto.setIdTransaction(tramite.getIdTransaction());
        dto.setType(tramite.getType());
        dto.setStatus(tramite.getStatus());
        return dto;
    }

    /**
     * Genera una entidad Tramite a partir de un DTO de petición.
     * @param dto objeto con datos proporcionados por el cliente
     * @return instancia de Tramite o null si dto es null
     */
    public static Tramite toTramite(TramiteRequestDTO dto) {
        if (dto == null)
            return null;
        Tramite tramite = new Tramite();
        tramite.setType(dto.getType());
        tramite.setStatus(dto.getStatus());
        return tramite;
    }
}