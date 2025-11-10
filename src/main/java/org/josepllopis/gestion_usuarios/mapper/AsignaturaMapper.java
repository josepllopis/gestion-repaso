package org.josepllopis.gestion_usuarios.mapper;

import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AsignaturaMapper {

    private ProfesorMapper profesorMapper;
    private AlumnoMapper alumnoMapper;


    public Asignatura toEntity(RequestAsignaturaDTO asignaturaDTO, Set<Profesor> profesores, Set<Alumno> alumnos){
        Asignatura asig = new Asignatura();
        asig.setNombre(asignaturaDTO.getNombre());

        if(alumnos != null && !alumnos.isEmpty()){
            asig.setAlumnos(alumnos);
        }

        if(profesores != null && !profesores.isEmpty()){
            asig.setProfesores(profesores);
        }

        return asig;
    }



    public ResponseAsignaturaDTO toResponse(Asignatura asignatura){
        ResponseAsignaturaDTO asignaturaDTO = new ResponseAsignaturaDTO();
        asignaturaDTO.setId(asignatura.getId());
        asignaturaDTO.setNombre(asignatura.getNombre());
        if(asignatura.getProfesores() != null && !asignatura.getProfesores().isEmpty()){
            asignaturaDTO.setProfesores(asignatura.getProfesores().stream()
                    .map(prof->profesorMapper.toRequest(prof)).collect(Collectors.toSet()));
        }
        if(asignatura.getAlumnos() != null && !asignatura.getAlumnos().isEmpty()){
            asignaturaDTO.setAlumnos(asignatura.getAlumnos().stream()
                    .map(alu->alumnoMapper.toRequest(alu)).collect(Collectors.toSet()));
        }

        return asignaturaDTO;
    }

    public RequestAsignaturaDTO toRequest(Asignatura asignatura){
        RequestAsignaturaDTO asignaturaDTO = new RequestAsignaturaDTO();
        asignaturaDTO.setNombre(asignatura.getNombre());

        return asignaturaDTO;
    }
}
