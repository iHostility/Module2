package com.hostile.module2.repo;

import com.hostile.module2.entity.Airport;
import com.hostile.module2.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flights, Long> {
    List<Flights> findByFromIdAndToId(Airport from, Airport to);
}
