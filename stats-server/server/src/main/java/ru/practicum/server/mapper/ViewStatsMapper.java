package ru.practicum.server.mapper;

import lombok.experimental.UtilityClass;
import ru.pracitcum.dto.ViewStatsDto;
import ru.practicum.server.model.ViewStats;

@UtilityClass
public class ViewStatsMapper {
    public static ViewStatsDto toDto(ViewStats vs) {
        return ViewStatsDto.builder()
                .app(vs.getApp())
                .uri(vs.getUri())
                .hits(vs.getCount())
                .build();
    }
}
