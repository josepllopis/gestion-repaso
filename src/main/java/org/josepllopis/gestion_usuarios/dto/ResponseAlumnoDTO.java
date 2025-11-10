package org.josepllopis.gestion_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Profesor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseAlumnoDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String localidad;
    private String tlfn;
    private String email;
    private Set<RequestProfesorDTO> profesores = new HashSet<>();
    private Set<RequestAsignaturaDTO> asignaturas = new HashSet<>();
}
