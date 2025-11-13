package org.josepllopis.gestion_usuarios.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.mapper.AlumnoMapper;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
import org.josepllopis.gestion_usuarios.mapper.ProfesorMapper;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private ProfesorRepository repoProfe;
    private ProfesorMapper mapperProfe;
    private AlumnoMapper mapperAlumno;
    private AsignaturaMapper mapperAsignatura;

    @Override
    @Transactional
    public List<ResponseProfesorDTO> getProfesores() {
        return repoProfe.findAll().stream().map(mapperProfe::toResponse).toList();
    }

    @Override
    @Transactional
    public Optional<ResponseProfesorDTO> getProfesor(Long id) {
        return repoProfe.findById(id).map(mapperProfe::toResponse);
    }

    @Override
    @Transactional
    public ResponseProfesorDTO addProfesor(RequestProfesorDTO profesorDTO) {
        Profesor prof = mapperProfe.toEntity(profesorDTO);
        Profesor profesorInsertado = repoProfe.save(prof);
        return mapperProfe.toResponse(profesorInsertado);
    }

    @Override
    @Transactional
    public Optional<ResponseProfesorDTO> updateProfesor(Long id, RequestProfesorDTO profesorDTO) {
        return repoProfe.findById(id).map(profesor -> {
            profesor.setNombre(profesorDTO.getNombre());
            profesor.setTlfn(profesorDTO.getTlfn());
            profesor.setLocalidad(profesorDTO.getLocalidad());
            profesor.setEmail(profesorDTO.getEmail());
            repoProfe.save(profesor);
            return mapperProfe.toResponse(profesor);
        });
    }

    @Override
    @Transactional
    public boolean deleteProfesor(Long id) {
        if (!repoProfe.existsById(id)) {
            return false;
        }
        repoProfe.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public List<ResponseAlumnoDTO> devolverAlumnos(Long id) {
        Profesor profesor = repoProfe.findById(id).orElseThrow(() ->
                new RuntimeException("Profesor no encontrado"));

        return profesor.getAlumnos().stream().map(mapperAlumno::toResponse).toList();
    }

    @Override
    public List<ResponseAsignaturaDTO> devolverAsignaturas(Long id) {
        Profesor profesor = repoProfe.findById(id).orElseThrow(() ->
                new RuntimeException("Profesor no encontrado"));

        return profesor.getAsignaturas().stream().map(mapperAsignatura::toResponse).toList();
    }
}
