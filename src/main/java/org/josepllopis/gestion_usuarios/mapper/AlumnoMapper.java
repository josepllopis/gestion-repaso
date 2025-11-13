package org.josepllopis.gestion_usuarios.mapper;


import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;

import org.springframework.stereotype.Component;


@Component
public class AlumnoMapper {


    public Alumno toEntity(RequestAlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setApellidos(alumnoDTO.getApellidos());
        alumno.setEmail(alumnoDTO.getEmail());
        alumno.setTlfn(alumnoDTO.getTlfn());
        alumno.setLocalidad(alumnoDTO.getLocalidad());
        return alumno;

    }

    public ResponseAlumnoDTO toResponse(Alumno alumno) {
        ResponseAlumnoDTO alumnoDTO = new ResponseAlumnoDTO();
        alumnoDTO.setId(alumno.getId());
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellidos(alumno.getApellidos());
        alumnoDTO.setEmail(alumno.getEmail());
        alumnoDTO.setTlfn(alumno.getTlfn());
        alumnoDTO.setLocalidad(alumno.getLocalidad());
        return alumnoDTO;
    }

}
