package com.example.statisticsservice.service;

import com.example.commondto.dto.AppAndUriDTO;
import com.example.commondto.dto.EndpointHitDto;
import com.example.commondto.dto.ViewStatsDto;
import com.example.statisticsservice.entity.EndpointHit;
import com.example.statisticsservice.entity.ViewStats;
import com.example.statisticsservice.mapper.EndpointHitMapper;
import com.example.statisticsservice.mapper.ViewStatsMapper;
import com.example.statisticsservice.repository.EndpointHitRepository;
import com.example.statisticsservice.repository.ViewStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService{
    private final EndpointHitRepository endpointHitRepository;
    private final ViewStatsRepository viewStatsRepository;
    private final ViewStatsMapper viewStatsMapper;
    private final EndpointHitMapper endpointHitMapper;


    public void registerHit(EndpointHitDto hit) {
        EndpointHit entity = endpointHitMapper.toEntity(hit);
        entity.setTimestamp(LocalDateTime.now());
        endpointHitRepository.save(entity);
        ViewStats stats = viewStatsRepository.findByAppAndUri(hit.getApp(), hit.getUri())
                .orElse(new ViewStats(null, hit.getApp(), hit.getUri(), 0, LocalDate.now()));
        stats.setHits(stats.getHits() + 1);
        viewStatsRepository.save(stats);
    }

    // Получение статистики по посещениям
    public List<ViewStatsDto> getStats(String start, String end, List<String> uris, boolean unique) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        if (unique) {
            List<AppAndUriDTO> uniqueStats = viewStatsRepository.findDistinctAppAndUri();
            return uniqueStats.stream().map(dto -> new ViewStatsDto(dto.getApp(), dto.getUri(), 0))
                    .collect(Collectors.toList());
        } else {
            List<ViewStats> stats = viewStatsRepository.findByDateBetweenAndUriIn(startDate, endDate, uris);
            return viewStatsMapper.toDtoList(stats);
        }
    }
}