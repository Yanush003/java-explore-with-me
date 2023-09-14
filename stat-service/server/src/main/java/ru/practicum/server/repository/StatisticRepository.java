package ru.practicum.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.server.model.EndpointHit;
import ru.practicum.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<EndpointHit, Long> {

    @Query( "SELECT new ru.practicum.server.model.ViewStats(sub.app, sub.uri, COUNT(sub.ip)) " +
            "FROM (" +
            "    SELECT e.app as app, e.uri as uri, e.ip as ip " +
            "    FROM EndpointHit e " +
            "    WHERE e.dateTime BETWEEN ?1 AND ?2 " +
            "    AND (e.uri IN (?3) OR (?3) is NULL) " +
            ") sub " +
            "GROUP BY sub.app, sub.uri")
    List<ViewStats> findViewStatList(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query( "SELECT new ru.practicum.server.model.ViewStats(sub.app, sub.uri, COUNT(DISTINCT sub.ip)) " +
            "FROM (" +
                    "    SELECT e.app as app, e.uri as uri, e.ip as ip " +
                    "    FROM EndpointHit e " +
                    "    WHERE e.dateTime BETWEEN ?1 AND ?2 " +
                    "    AND (e.uri IN (?3) OR (?3) is NULL) " +
                    ") sub " +
                    "GROUP BY sub.app, sub.uri")
    List<ViewStats> findViewStatListUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);

}
