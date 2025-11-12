package org.josepllopis.gestion_usuarios.mapper;

import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProfesorMapper {


    public Profesor toEntity(RequestProfesorDTO profesorDTO){
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setLocalidad(profesorDTO.getLocalidad());
        profesor.setEmail(profesorDTO.getEmail());
        profesor.setTlfn(profesorDTO.getTlfn());
        return profesor;

    }

    public ResponseProfesorDTO toResponse(Profesor profesor){
        ResponseProfesorDTO profesorDTO = new ResponseProfesorDTO();
        profesorDTO.setId(profesor.getId());
        profesorDTO.setNombre(profesor.getNombre());
        profesorDTO.setLocalidad(profesor.getLocalidad());
        profesorDTO.setTlfn(profesor.getTlfn());
        profesorDTO.setEmail(profesor.getEmail());

        return profesorDTO;
    }

}
