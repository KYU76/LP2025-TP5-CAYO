package dev.labintec.tramite.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Entidad JPA que corresponde a la tabla "transaction" en la base de datos.
 * Define el identificador, el tipo de trámite y su estado
 * @author Quique
 */
@Entity                    // JPA: marca la clase como entidad para persistencia
@Table(name = "transaction") // JPA: especifica el nombre de la tabla en la base de datos
@Data                      // Lombok: genera getters, setters, equals, hashCode y toString
@NoArgsConstructor         // Lombok: crea un constructor público sin argumentos
@AllArgsConstructor        // Lombok: crea un constructor con argumentos para todos los campos
@RequiredArgsConstructor   // Lombok: crea un constructor solo para campos anotados @NonNull
public class Tramite {
    /**
     * Clave primaria autoincremental.
     */
    @Id                                             // JPA: define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA: la BD asigna el valor (auto‐incremental)
    @Column(name = "id_transaction")                // JPA: mapea este campo a la columna "id_user"
    private Long idTransaction;

    /**
     * Tipo de trámite.
     */
    @NonNull                                        // Lombok: marca el campo como obligatorio para RequiredArgsConstructor
    @Column(name = "type")                          // JPA: mapea a columna "type"
    private String type;
    
    /**
     * Estado del trámite.
     */
    @NonNull                                        // Lombok: incluye este campo en el constructor requerido
    @Column(name = "status")                        // JPA: mapea a columna "status"
    private Boolean status;
}