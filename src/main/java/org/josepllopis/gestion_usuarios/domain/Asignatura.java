package org.josepllopis.gestion_usuarios.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Asignatura")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
        if (!(o instanceof Asignatura that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(profesores, that.profesores) && Objects.equals(alumnos, that.alumnos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, profesores, alumnos);
    }
}
