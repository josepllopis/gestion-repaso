package org.josepllopis.gestion_usuarios.service;

import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;


import java.util.List;
import java.util.Optional;


public interface ProfesorService {

    List<ResponseProfesorDTO> getProfesores();
    Optional<ResponseProfesorDTO> getProfesor(Long id);
    ResponseProfesorDTO addProfesor(RequestProfesorDTO profesorDTO);
    Optional<ResponseProfesorDTO> updateProfesor(Long id, RequestProfesorDTO profesorDTO);
    boolean deleteProfesor(Long id);
    List<ResponseAlumnoDTO> devolverAlumnos(Long id);
    List<ResponseAsignaturaDTO> devolverAsignaturas(Long id);
}
