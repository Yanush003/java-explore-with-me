package ru.practicum.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.server.model.EndpointHit;

@Repository
public interface StatisticRepository extends JpaRepository<EndpointHit, Long> {

}
