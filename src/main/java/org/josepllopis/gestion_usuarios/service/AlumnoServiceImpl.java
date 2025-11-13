package org.josepllopis.gestion_usuarios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.mapper.AlumnoMapper;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
import org.josepllopis.gestion_usuarios.mapper.ProfesorMapper;
import org.josepllopis.gestion_usuarios.repositories.AlumnoRepository;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;
    private final ProfesorMapper profesorMapper;
    private final AsignaturaMapper asignaturaMapper;
    private final ProfesorRepository profesorRepository;

    @Override
    @Transactional
    public List<ResponseAlumnoDTO> getAlumnos() {
        return alumnoRepository.findAll().stream().map(alumnoMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public Optional<ResponseAlumnoDTO> getAlumno(Long id) {
        return alumnoRepository.findById(id).map(alumnoMapper::toResponse);
    }

    @Override
    @Transactional
    public ResponseAlumnoDTO addAlumno(RequestAlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoMapper.toEntity(alumnoDTO);
        Alumno alumnoCreado = alumnoRepository.save(alumno);
        return alumnoMapper.toResponse(alumnoCreado);
    }

    @Override
    @Transactional
    public Optional<ResponseAlumnoDTO> updateAlumno(Long id, RequestAlumnoDTO alumnoDTO) {
        return alumnoRepository.findById(id).map(alu->
        {
            alu.setNombre(alumnoDTO.getNombre());
            alu.setApellidos(alumnoDTO.getApellidos());
            alu.setLocalidad(alumnoDTO.getLocalidad());
            alu.setTlfn(alumnoDTO.getTlfn());
            alu.setEmail(alumnoDTO.getEmail());
            alumnoRepository.save(alu);
            return alumnoMapper.toResponse(alu);
        });
    }

    @Override
    @Transactional
    public boolean deleteAlumno(Long id) {
        if(!alumnoRepository.existsById(id)){
            return false;
        }

        alumnoRepository.deleteById(id);
        return true;
    }

    @Override
    public ResponseAlumnoDTO asignarAlumno(Long idProfesor, Long idAlumno) {

        Profesor profesor = profesorRepository.findById(idProfesor).orElseThrow(()->
                new RuntimeException("Profesor no encontrado"));
        Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(()->
                new RuntimeException("Alumno encontrado"));



        if(profesor.getAlumnos().contains(alumno)){
            throw new RuntimeException("El alumno ya está vinculado al profesor");
        }

        profesor.getAlumnos().add(alumno);

        profesorRepository.save(profesor);

        return alumnoMapper.toResponse(alumno);

    }

    @Override
    public ResponseAlumnoDTO desAsignarAlumno(Long idProfesor, Long idAlumno) {

        Profesor profesor = profesorRepository.findById(idProfesor).orElseThrow(()->
                new RuntimeException("Profesor no encontrado"));
        Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(()->
                new RuntimeException("Alumno encontrado"));



        if(profesor.getAlumnos().contains(alumno)){
            profesor.getAlumnos().remove(alumno);

            profesorRepository.save(profesor);
        }else{
            throw new RuntimeException("El alumno no está vinculado a ese profesor");
        }



        return alumnoMapper.toResponse(alumno);

    }

    @Override
    public List<ResponseProfesorDTO> devolverProfesores(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()->
                new RuntimeException("No existe el alumno"));

        return alumno.getProfesores().stream().map(profesorMapper::toResponse).toList();
    }

    @Override
    public List<ResponseAsignaturaDTO> devolverAsignaturas(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()->
                new RuntimeException("No existe el alumno"));

        return alumno.getAsignaturas().stream().map(asignaturaMapper::toResponse).toList();
    }
}
