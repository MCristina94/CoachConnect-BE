package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Estudiante;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Estudiante est " +
            "SET est.nivelEducativo = :nivelEducativo " +
            "WHERE est.id = :id")
    Integer update(@Param("id") Long id, @Param("nivelEducativo") String nivelEducativo);



    Optional<Estudiante> findEstudianteById(Long id);

    @Query("SELECT e from Estudiante e WHERE e.usuario.id = :usuarioId")
    List<Estudiante> findEstudianteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
