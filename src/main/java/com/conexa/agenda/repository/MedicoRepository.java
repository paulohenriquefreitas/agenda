package com.conexa.agenda.repository;

import com.conexa.agenda.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("SELECT t FROM Medico t WHERE t.nome = ?1")
    Optional<Medico> findByName(String nome);

    @Query("SELECT t FROM Medico t WHERE t.token = ?1")
    Medico findByToken(String nome);
}