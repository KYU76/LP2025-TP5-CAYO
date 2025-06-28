package dev.labintec.usuario.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


/**
 * Entidad JPA que corresponde a la tabla "user" en la base de datos.
 * Define el identificador, el nombre de usuario y la contraseña
 * @author Quique
 */
@Entity                    // JPA: marca la clase como entidad para persistencia
@Table(name = "user")      // JPA: especifica el nombre de la tabla en la base de datos
@Data                      // Lombok: genera getters, setters, equals, hashCode y toString
@NoArgsConstructor         // Lombok: crea un constructor público sin argumentos
@AllArgsConstructor        // Lombok: crea un constructor con argumentos para todos los campos
@RequiredArgsConstructor   // Lombok: crea un constructor solo para campos anotados @NonNull
public class Usuario {
    /**
     * Clave primaria autoincremental.
     */
    @Id                                             // JPA: define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA: la BD asigna el valor (auto‐incremental)
    @Column(name = "id_user")                       // JPA: mapea este campo a la columna "id_user"
    private Long id_user;

    /**
     * Nombre de usuario único.
     */
    @NonNull                                        // Lombok: marca el campo como obligatorio para RequiredArgsConstructor
    @Column(name = "username")                      // JPA: mapea a columna "username"
    private String username;

    /**
     * Contraseña cifrada.
     */
    @NonNull                                        // Lombok: incluye este campo en el constructor requerido
    @Column(name = "password")                      // JPA: mapea a columna "password"
    private String password;
}