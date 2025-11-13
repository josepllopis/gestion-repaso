package org.josepllopis.gestion_usuarios.service;

import org.josepllopis.gestion_usuarios.dto.*;

import java.util.List;
import java.util.Optional;

public interface AsignaturaService {

    List<ResponseAsignaturaDTO> getAsignaturas();
    Optional<ResponseAsignaturaDTO> getAsignatura(Long id);
    ResponseAsignaturaDTO createAsignatura(RequestAsignaturaDTO asignaturaDTO);
    Optional<ResponseAsignaturaDTO> updateAsignatura(Long id, RequestAsignaturaDTO asignaturaDTO);
    boolean deleteAsignatura(Long id);
    ResponseAsignaturaDTO asignarAsignaturaProf(Long idProfesor, Long idAsignatura);
    ResponseAsignaturaDTO asignarAsignaturaAlumn(Long idAlumno, Long idAsignatura);
    ResponseAsignaturaDTO desAsignarAsignaturaProf(Long idProfesor, Long idAsignatura);
    ResponseAsignaturaDTO desAsignarAsignaturaAlumn(Long idAlumno, Long idAsignatura);
    List<ResponseProfesorDTO> devolverProfesores(Long id);
    List<ResponseAlumnoDTO> devolverAlumnos(Long id);
}
