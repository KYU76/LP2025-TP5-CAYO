package dev.labintec.tramite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datos recibidos en las peticiones de creación o actualización de trámite.
 * Incluye el tipo y el estado de trámite.
 * @author Quique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TramiteRequestDTO {

    /**
     * Tipo único que identifica al trámite.
     */
    @NotBlank(message = "El tipo de trámite es obligatorio")
    private String type;

    /**
     * Estado del trámite.
     */
    @NotNull(message = "El estado del trámite es obligatorio")
    private Boolean status;
}