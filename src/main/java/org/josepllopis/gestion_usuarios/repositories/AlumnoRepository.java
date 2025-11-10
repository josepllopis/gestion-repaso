package org.josepllopis.gestion_usuarios.repositories;

import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno,Long> {
}
