package ru.practicum.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pracitcum.dto.EndpointHitDto;
import ru.pracitcum.dto.ViewStatsDto;
import ru.practicum.server.mapper.ViewStatsMapper;
import ru.practicum.server.model.EndpointHit;
import ru.practicum.server.model.ViewStats;
import ru.practicum.server.repository.StatisticRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static ru.practicum.server.mapper.EndpointHitMapper.toDto;
import static ru.practicum.server.mapper.EndpointHitMapper.toEntity;
@Service
@AllArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public List<ViewStatsDto> getStatList(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        return (unique ?
                findViewStatListUniqueIp(start, end, uris).stream()
                        .sorted(comparingLong(ViewStats::getCount).reversed())
                        .collect(Collectors.toList())
                :
                findViewStatList(start, end, uris)).stream()
                .sorted(comparingLong(ViewStats::getCount).reversed())
                .map(ViewStatsMapper::toDto)
                .collect(Collectors.toList());
    }

    public EndpointHitDto registerHit(EndpointHitDto dto) {
        return toDto(statisticRepository.save(toEntity(dto)));
    }

    public List<ViewStats> findViewStatList(LocalDateTime start, LocalDateTime end, List<String> uris) {
        // Получаем все записи EndpointHit в заданном диапазоне времени и с определенными uris.
        List<EndpointHit> filteredHits = getEndpointHits(start, end, uris);
        // Группируем записи по app и uri, и подсчитываем количество записей для каждой группы.
        Map<String, Map<String, Long>> groupedStats = filteredHits.stream()
                .collect(Collectors.groupingBy(EndpointHit::getApp,
                        Collectors.groupingBy(EndpointHit::getUri, Collectors.counting())));
        // Создаем список ViewStats на основе сгруппированных данных.
        List<ViewStats> viewStatsList = new ArrayList<>();
        groupedStats.forEach((app, uriCountMap) -> {
            uriCountMap.forEach((uri, count) -> {
                viewStatsList.add(new ViewStats(app, uri, count));
            });
        });

        return viewStatsList;
    }

    private List<EndpointHit> getEndpointHits(LocalDateTime start, LocalDateTime end, List<String> uris) {
        List<EndpointHit> endpointHits = statisticRepository.findAll();
        // Фильтруем записи по условиям (e.dateTime BETWEEN start AND end) и (e.uri IN uris или uris is NULL)
        return endpointHits.stream()
                .filter(hit -> hit.getDateTime().isAfter(start) && hit.getDateTime().isBefore(end) &&
                        (uris == null || uris.isEmpty() || uris.contains(hit.getUri())))
                .collect(Collectors.toList());
    }

    public List<ViewStats> findViewStatListUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris) {
        // Получаем все записи EndpointHit в заданном диапазоне времени и с определенными uris
        List<EndpointHit> filteredHits = getEndpointHits(start, end, uris);
        // Группируем записи по app и uri, и подсчитываем количество уникальных IP для каждой группы.
        Map<String, Map<String, Set<String>>> groupedStats = filteredHits.stream()
                .collect(Collectors.groupingBy(EndpointHit::getApp,
                        Collectors.groupingBy(EndpointHit::getUri,
                                Collectors.mapping(EndpointHit::getIp, Collectors.toSet()))));
        // Создаем список ViewStats на основе сгруппированных данных
        List<ViewStats> viewStatsList = new ArrayList<>();
        groupedStats.forEach((app, uriIpSetMap) -> {
            uriIpSetMap.forEach((uri, ipSet) -> {
                viewStatsList.add(new ViewStats(app, uri, (long) ipSet.size()));
            });
        });
        return viewStatsList;
    }
}