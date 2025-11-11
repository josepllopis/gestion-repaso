package org.josepllopis.gestion_usuarios.repositories;

import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
}
