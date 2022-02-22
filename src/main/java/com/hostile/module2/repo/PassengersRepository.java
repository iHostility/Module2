package com.hostile.module2.repo;

import com.hostile.module2.entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengersRepository extends JpaRepository<Passengers, Long> {
}
