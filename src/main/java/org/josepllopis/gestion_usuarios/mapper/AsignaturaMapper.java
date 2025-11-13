package org.josepllopis.gestion_usuarios.mapper;


import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;

import org.springframework.stereotype.Component;


@Component
public class AsignaturaMapper {


    public Asignatura toEntity(RequestAsignaturaDTO asignaturaDTO) {
        Asignatura asig = new Asignatura();
        asig.setNombre(asignaturaDTO.getNombre());
        return asig;
    }


    public ResponseAsignaturaDTO toResponse(Asignatura asignatura) {
        ResponseAsignaturaDTO asignaturaDTO = new ResponseAsignaturaDTO();
        asignaturaDTO.setId(asignatura.getId());
        asignaturaDTO.setNombre(asignatura.getNombre());
        return asignaturaDTO;
    }


}
