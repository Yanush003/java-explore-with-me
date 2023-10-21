package ru.practicum.ewmmain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewmmain.dto.LocationDto;
import ru.practicum.ewmmain.model.Location;

@Mapper
public interface LocationMapper {
    LocationMapper LOCATION_MAPPER = Mappers.getMapper(LocationMapper.class);

    Location fromDto(LocationDto dto);
}
