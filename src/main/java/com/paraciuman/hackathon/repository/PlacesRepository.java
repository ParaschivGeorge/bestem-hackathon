package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PlacesRepository extends JpaRepository<Places, Integer> {
}
