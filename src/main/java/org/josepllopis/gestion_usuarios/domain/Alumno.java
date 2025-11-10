package org.josepllopis.gestion_usuarios.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

}
