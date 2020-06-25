package com.test.solarsystem.repositorio;

import com.test.solarsystem.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
