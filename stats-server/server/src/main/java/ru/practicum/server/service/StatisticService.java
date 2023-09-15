package ru.practicum.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pracitcum.dto.EndpointHitDto;
import ru.pracitcum.dto.ViewStatsDto;
import ru.practicum.server.mapper.ViewStatsMapper;
import ru.practicum.server.model.ViewStats;
import ru.practicum.server.repository.StatisticRepository;

import java.time.LocalDateTime;
import java.util.List;
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
                statisticRepository.findViewStatListUniqueIp(start, end, uris).stream()
                        .sorted(comparingLong(ViewStats::getHits).reversed())
                        .collect(Collectors.toList())
                :
                statisticRepository.findViewStatList(start, end, uris)).stream()
                        .sorted(comparingLong(ViewStats::getHits).reversed())
                        .map(ViewStatsMapper::toDto)
                        .collect(Collectors.toList());
    }

    public EndpointHitDto registerHit(EndpointHitDto dto) {
        return toDto(statisticRepository.save(toEntity(dto)));
    }
}
