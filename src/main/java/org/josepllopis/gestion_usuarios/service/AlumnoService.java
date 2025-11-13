package org.josepllopis.gestion_usuarios.service;

import org.josepllopis.gestion_usuarios.dto.*;

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
    List<ResponseProfesorDTO> devolverProfesores(Long id);
    List<ResponseAsignaturaDTO> devolverAsignaturas(Long id);
}
