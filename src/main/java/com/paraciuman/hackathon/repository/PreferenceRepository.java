package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PreferenceRepository extends JpaRepository<Preference,Long> {
}
