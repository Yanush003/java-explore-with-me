package com.example.statisticsservice.mapper;

import com.example.commondto.dto.ViewStatsDto;
import com.example.statisticsservice.entity.ViewStats;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewStatsMapper {
    ViewStatsDto toDto(ViewStats viewStats);
    ViewStats toEntity(ViewStatsDto viewStatsDto);
    List<ViewStatsDto> toDtoList (List<ViewStats> viewStats);
}
