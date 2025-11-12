package org.josepllopis.gestion_usuarios.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Alumno")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false, length = 180)
    private String localidad;
    @Column(nullable = false, length = 150)
    private String tlfn;
    @Column(nullable = false, length = 150)
    private String email;

    @ManyToMany(mappedBy = "alumnos")
    private Set<Profesor> profesores = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="alumno_asignatura",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private Set<Asignatura> asignaturas = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(id, alumno.id) && Objects.equals(nombre, alumno.nombre) && Objects.equals(apellidos, alumno.apellidos) && Objects.equals(localidad, alumno.localidad) && Objects.equals(tlfn, alumno.tlfn) && Objects.equals(email, alumno.email) && Objects.equals(profesores, alumno.profesores) && Objects.equals(asignaturas, alumno.asignaturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, localidad, tlfn, email, profesores, asignaturas);
    }
}
