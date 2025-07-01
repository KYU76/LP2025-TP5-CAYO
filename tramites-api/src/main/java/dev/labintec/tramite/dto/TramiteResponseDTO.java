package dev.labintec.tramite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datos de tramite devueltos en la respuesta de la API.
 * Contiene el identificador único, el tipo y el estado asociado.
 * @author Quique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TramiteResponseDTO {

    /**
     * Identificador asignado en la base de datos.
     */
    private Long idTransaction;

    /**
     * Tipo único que identifica al trámite.
     */
    @NotBlank(message = "El tipo de trámite es obligatorio")
    private String type;

    /**
     * Estado del trámite.
     */
    @NotBlank(message = "El estado del trámite es obligatorio")
    private Boolean status;
}
