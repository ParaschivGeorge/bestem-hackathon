package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DayRepository extends JpaRepository<Day,Long> {
}
