package org.josepllopis.gestion_usuarios.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Profesor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor = (Profesor) o;
        return Objects.equals(id, profesor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
