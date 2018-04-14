package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Agenda;
import com.paraciuman.hackathon.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface PreferenceRepository extends JpaRepository<Preference,Long> {
    ArrayList<Preference> findByAgenda(Agenda agenda);
    void deleteById(long id);
    Preference findById(long id);
}
