package ru.practicum.server.mapper;

import lombok.experimental.UtilityClass;
import ru.pracitcum.dto.EndpointHitDto;
import ru.practicum.server.model.EndpointHit;

@UtilityClass
public class EndpointHitMapper {
    public static EndpointHit toEntity(EndpointHitDto dto) {
        return EndpointHit.builder()
                .id(dto.getId())
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .dateTime(dto.getTimestamp())
                .build();
    }

    public static EndpointHitDto toDto(EndpointHit endpointHit) {
        return EndpointHitDto.builder()
                .id(endpointHit.getId())
                .app(endpointHit.getApp())
                .uri(endpointHit.getUri())
                .ip(endpointHit.getIp())
                .timestamp(endpointHit.getDateTime())
                .build();
    }
}
