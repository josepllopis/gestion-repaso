package org.josepllopis.gestion_usuarios.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Asignatura")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @ManyToMany(mappedBy = "asignaturas")
    private Set<Profesor> profesores = new HashSet<>();
    @ManyToMany(mappedBy = "asignaturas")
    private Set<Alumno> alumnos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura asignatura = (Asignatura) o;
        return Objects.equals(id, asignatura.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
