package com.example.statisticsservice.mapper;

import com.example.commondto.dto.EndpointHitDto;
import com.example.statisticsservice.entity.EndpointHit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EndpointHitMapper {
    EndpointHitDto toDto(EndpointHit endpointHit);
    EndpointHit toEntity(EndpointHitDto endpointHitDto);
}

