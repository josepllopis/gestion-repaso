package org.josepllopis.gestion_usuarios.mapper;

import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProfesorMapper {

    private AlumnoMapper alumnoMapper;
    private AsignaturaMapper asignaturaMapper;

    public Profesor toEntity(RequestProfesorDTO profesorDTO, Set<Alumno> alumnos, Set<Asignatura>asignaturas){
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setLocalidad(profesorDTO.getLocalidad());
        profesor.setEmail(profesorDTO.getEmail());
        profesor.setTlfn(profesorDTO.getTlfn());

        if(alumnos != null && !alumnos.isEmpty()){
            profesor.setAlumnos(alumnos);
        }
        if(asignaturas!= null && !asignaturas.isEmpty()){
            profesor.setAsignaturas(asignaturas);
        }

        return profesor;

    }

    public ResponseProfesorDTO toResponse(Profesor profesor){
        ResponseProfesorDTO profesorDTO = new ResponseProfesorDTO();
        profesorDTO.setId(profesor.getId());
        profesorDTO.setNombre(profesor.getNombre());
        profesorDTO.setLocalidad(profesor.getLocalidad());
        profesorDTO.setTlfn(profesor.getTlfn());
        profesorDTO.setEmail(profesor.getEmail());

        if(profesor.getAlumnos() != null && !profesor.getAlumnos().isEmpty()){
            profesorDTO.setAlumnos(
                    profesor.getAlumnos().stream().
                            map(alu->alumnoMapper.toRequest(alu))
                            .collect(Collectors.toSet())
            );
        }

        if(profesor.getAsignaturas() != null && !profesor.getAsignaturas().isEmpty()){
            profesorDTO.setAsignaturas(
                    profesor.getAsignaturas().stream()
                            .map(asi->asignaturaMapper.toRequest(asi))
                            .collect(Collectors.toSet()));
        }

        return profesorDTO;
    }

    public RequestProfesorDTO toRequest(Profesor profesor){
        RequestProfesorDTO profesorDTO = new RequestProfesorDTO();
        profesorDTO.setNombre(profesor.getNombre());
        profesorDTO.setLocalidad(profesor.getLocalidad());
        profesorDTO.setEmail(profesor.getEmail());
        profesorDTO.setTlfn(profesor.getTlfn());

        return profesorDTO;
    }
}
