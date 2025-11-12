package org.josepllopis.gestion_usuarios.mapper;

import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AsignaturaMapper {


    public Asignatura toEntity(RequestAsignaturaDTO asignaturaDTO){
        Asignatura asig = new Asignatura();
        asig.setNombre(asignaturaDTO.getNombre());
        return asig;
    }



    public ResponseAsignaturaDTO toResponse(Asignatura asignatura){
        ResponseAsignaturaDTO asignaturaDTO = new ResponseAsignaturaDTO();
        asignaturaDTO.setId(asignatura.getId());
        asignaturaDTO.setNombre(asignatura.getNombre());
        return asignaturaDTO;
    }


}
