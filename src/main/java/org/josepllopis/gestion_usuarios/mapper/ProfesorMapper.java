package org.josepllopis.gestion_usuarios.mapper;


import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.springframework.stereotype.Component;


@Component
public class ProfesorMapper {


    public Profesor toEntity(RequestProfesorDTO profesorDTO) {
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setLocalidad(profesorDTO.getLocalidad());
        profesor.setEmail(profesorDTO.getEmail());
        profesor.setTlfn(profesorDTO.getTlfn());
        return profesor;

    }

    public ResponseProfesorDTO toResponse(Profesor profesor) {
        ResponseProfesorDTO profesorDTO = new ResponseProfesorDTO();
        profesorDTO.setId(profesor.getId());
        profesorDTO.setNombre(profesor.getNombre());
        profesorDTO.setLocalidad(profesor.getLocalidad());
        profesorDTO.setTlfn(profesor.getTlfn());
        profesorDTO.setEmail(profesor.getEmail());
        return profesorDTO;
    }

}
