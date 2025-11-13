package org.josepllopis.gestion_usuarios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.mapper.AlumnoMapper;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
import org.josepllopis.gestion_usuarios.mapper.ProfesorMapper;
import org.josepllopis.gestion_usuarios.repositories.AlumnoRepository;
import org.josepllopis.gestion_usuarios.repositories.AsignaturaRepository;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final AsignaturaMapper asignaturaMapper;
    private final ProfesorMapper profesorMapper;
    private final AlumnoMapper alumnoMapper;
    private final ProfesorRepository profesorRepository;
    private final AlumnoRepository alumnoRepository;

    @Override
    @Transactional
    public List<ResponseAsignaturaDTO> getAsignaturas() {
        return asignaturaRepository.findAll().stream().map(asignaturaMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public Optional<ResponseAsignaturaDTO> getAsignatura(Long id) {
        return asignaturaRepository.findById(id).map(asignaturaMapper::toResponse);
    }

    @Override
    @Transactional
    public ResponseAsignaturaDTO createAsignatura(RequestAsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = asignaturaMapper.toEntity(asignaturaDTO);
        Asignatura asignaturaCreada = asignaturaRepository.save(asignatura);
        return asignaturaMapper.toResponse(asignaturaCreada);
    }

    @Override
    @Transactional
    public Optional<ResponseAsignaturaDTO> updateAsignatura(Long id, RequestAsignaturaDTO asignaturaDTO) {
        return asignaturaRepository.findById(id).map(asig -> {
            asig.setNombre(asignaturaDTO.getNombre());
            asignaturaRepository.save(asig);
            return asignaturaMapper.toResponse(asig);
        });
    }

    @Override
    @Transactional
    public boolean deleteAsignatura(Long id) {
        if (!asignaturaRepository.existsById(id)) {
            return false;
        }

        asignaturaRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public ResponseAsignaturaDTO asignarAsignaturaProf(Long idProfesor, Long idAsignatura) {
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(idAsignatura)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));


        // Solo agregamos si no está ya
        if (!profesor.getAsignaturas().contains(asignatura)) {
            profesor.getAsignaturas().add(asignatura);
        } else {
            throw new RuntimeException("La vinculación ya está hecha");
        }

        // Guardamos al profesor, Hibernate persistirá la relación ManyToMany
        profesorRepository.save(profesor);

        return asignaturaMapper.toResponse(asignatura);
    }

    @Override
    public ResponseAsignaturaDTO asignarAsignaturaAlumn(Long idAlumno, Long idAsignatura) {
        Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado"));
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() ->
                new RuntimeException("Asignatura no encontrada"));

        if (alumno.getAsignaturas().contains(asignatura)) {
            throw new RuntimeException("El alumno ya está vinculado a esta asignatura");
        }

        alumno.getAsignaturas().add(asignatura);

        alumnoRepository.save(alumno);

        return asignaturaMapper.toResponse(asignatura);
    }

    @Override
    public ResponseAsignaturaDTO desAsignarAsignaturaProf(Long idProfesor, Long idAsignatura) {
        Profesor profesor = profesorRepository.findById(idProfesor).orElseThrow(() ->
                new RuntimeException("Profesor no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() ->
                new RuntimeException("Asignatura no encontrada"));


        if (profesor.getAsignaturas().contains(asignatura)) {
            profesor.getAsignaturas().remove(asignatura);
            profesorRepository.save(profesor);
        } else {
            throw new RuntimeException("El profesor no está asignado a la asignatura");
        }

        return asignaturaMapper.toResponse(asignatura);
    }

    @Override
    public ResponseAsignaturaDTO desAsignarAsignaturaAlumn(Long idAlumno, Long idAsignatura) {
        Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() ->
                new RuntimeException("Asignatura no encontrada"));

        if (alumno.getAsignaturas().contains(asignatura)) {
            alumno.getAsignaturas().remove(asignatura);
            alumnoRepository.save(alumno);
        } else {
            throw new RuntimeException("El alumno no está vinculado a la asignatura");
        }

        return asignaturaMapper.toResponse(asignatura);
    }

    @Override
    public List<ResponseProfesorDTO> devolverProfesores(Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No existe esta asignatura"));

        return asignatura.getProfesores().stream().map(profesorMapper::toResponse).toList();
    }

    @Override
    public List<ResponseAlumnoDTO> devolverAlumnos(Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No existe esta asignatura"));

        return asignatura.getAlumnos().stream().map(alumnoMapper::toResponse).toList();
    }


}
