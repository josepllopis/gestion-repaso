package org.josepllopis.gestion_usuarios.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

}
