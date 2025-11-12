package org.josepllopis.gestion_usuarios.service;

import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;

import java.util.List;
import java.util.Optional;

public interface AsignaturaService {

    List<ResponseAsignaturaDTO> getAsignaturas();
    Optional<ResponseAsignaturaDTO> getAsignatura(Long id);
    ResponseAsignaturaDTO createAsignatura(RequestAsignaturaDTO asignaturaDTO);
    Optional<ResponseAsignaturaDTO> updateAsignatura(Long id, ResponseAsignaturaDTO asignaturaDTO);
    boolean deleteAsignatura(Long id);
    ResponseAsignaturaDTO asignarAsignaturaProf(Long idProfesor, Long idAsignatura);
    ResponseAsignaturaDTO asignarAsignaturaAlumn(Long idAlumno, Long idAsignatura);
    ResponseAsignaturaDTO desAsignarAsignaturaProf(Long idProfesor, Long idAsignatura);
    ResponseAsignaturaDTO desAsignarAsignaturaAlumn(Long idAlumno, Long idAsignatura);
}
