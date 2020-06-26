package com.test.solarsystem.repositorio;

import com.test.solarsystem.constant.PeriodType;
import com.test.solarsystem.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findAllByPeriodType(PeriodType periodType);

}
