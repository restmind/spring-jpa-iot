package com.shuplat.spring.repository;

import com.shuplat.spring.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Integer> {
}
