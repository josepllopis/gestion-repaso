package org.josepllopis.gestion_usuarios.service;

import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<ResponseAlumnoDTO> getAlumnos();
    Optional<ResponseAlumnoDTO> getAlumno(Long id);
    ResponseAlumnoDTO addAlumno(RequestAlumnoDTO alumnoDTO);
    Optional<ResponseAlumnoDTO> updateAlumno(Long id, RequestAlumnoDTO alumnoDTO);
    boolean deleteAlumno(Long id);
    ResponseAlumnoDTO asignarAlumno(Long idProfesor,Long idAlumno);
    ResponseAlumnoDTO desAsignarAlumno(Long idProfesor, Long idAlumno);
}
