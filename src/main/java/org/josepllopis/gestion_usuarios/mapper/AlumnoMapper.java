package org.josepllopis.gestion_usuarios.mapper;

import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AlumnoMapper {



    @Autowired
    @Lazy private AsignaturaMapper asignaturaMapper;

    @Autowired
    @Lazy private ProfesorMapper profesorMapper;

    public Alumno toEntity(RequestAlumnoDTO alumnoDTO, Set<Profesor> profesores, Set<Asignatura> asignaturas){
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setApellidos(alumnoDTO.getApellidos());
        alumno.setEmail(alumnoDTO.getEmail());
        alumno.setTlfn(alumnoDTO.getTlfn());
        alumno.setLocalidad(alumnoDTO.getLocalidad());

        if(profesores != null && !profesores.isEmpty()){
            alumno.setProfesores(profesores);
        }

        if(asignaturas != null && !asignaturas.isEmpty()){
            alumno.setAsignaturas(asignaturas);
        }

        return alumno;

    }

    public ResponseAlumnoDTO toResponse(Alumno alumno){
        ResponseAlumnoDTO alumnoDTO = new ResponseAlumnoDTO();
        alumnoDTO.setId(alumno.getId());
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellidos(alumno.getApellidos());
        alumnoDTO.setEmail(alumno.getEmail());
        alumnoDTO.setTlfn(alumno.getTlfn());
        alumnoDTO.setLocalidad(alumno.getLocalidad());

        if(alumno.getProfesores() != null && !alumno.getProfesores().isEmpty()){
            alumnoDTO.setProfesores(alumno.getProfesores()
                    .stream().map(profe->profesorMapper.toRequest(profe)).collect(Collectors.toSet()));
        }

        if(alumno.getAsignaturas() != null && !alumno.getAsignaturas().isEmpty()){
            alumnoDTO.setAsignaturas(alumno.getAsignaturas().stream()
                    .map(asig->asignaturaMapper.toRequest(asig)).collect(Collectors.toSet()));
        }

        return alumnoDTO;
    }

    public RequestAlumnoDTO toRequest(Alumno alumno){
        RequestAlumnoDTO alumnoDTO = new RequestAlumnoDTO();
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellidos(alumno.getApellidos());
        alumnoDTO.setEmail(alumno.getEmail());
        alumnoDTO.setTlfn(alumno.getTlfn());
        alumnoDTO.setLocalidad(alumno.getLocalidad());

        return alumnoDTO;
    }
}
