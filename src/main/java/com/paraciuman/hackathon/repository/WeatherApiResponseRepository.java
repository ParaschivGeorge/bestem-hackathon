package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.WeatherApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface WeatherApiResponseRepository extends JpaRepository<WeatherApiResponse, Long> {
}
