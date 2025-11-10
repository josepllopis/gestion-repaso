package org.josepllopis.gestion_usuarios.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Profesor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String tlfn;
    private String localidad;

    @ManyToMany
    @JoinTable(
            name = "profesor_alumno",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")

    )
    private Set<Alumno> alumnos = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "profesor_asignatura",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")

    )
    private Set<Asignatura> asignaturas = new HashSet<>();
}
