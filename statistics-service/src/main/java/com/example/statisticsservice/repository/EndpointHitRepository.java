package com.example.statisticsservice.repository;

import com.example.statisticsservice.entity.EndpointHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointHitRepository extends JpaRepository<EndpointHit, Integer> {
}
