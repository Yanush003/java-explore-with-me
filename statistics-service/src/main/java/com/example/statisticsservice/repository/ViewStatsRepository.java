package com.example.statisticsservice.repository;

import com.example.commondto.dto.AppAndUriDTO;
import com.example.statisticsservice.entity.ViewStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViewStatsRepository extends JpaRepository<ViewStats, Integer> {

    @Query("SELECT DISTINCT new AppAndUriDTO(v.app, v.uri) FROM ViewStats v")
    List<AppAndUriDTO> findDistinctAppAndUri();

    Optional<ViewStats> findByAppAndUri(String app, String uri);

    List<ViewStats> findByDateBetweenAndUriIn(LocalDate startDate, LocalDate endDate, List<String> uris);
}


