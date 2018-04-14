package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
