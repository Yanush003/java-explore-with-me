package ru.practicum.ewmmain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewmmain.dto.EventFullDto;
import ru.practicum.ewmmain.dto.EventShortDto;
import ru.practicum.ewmmain.dto.NewEventDto;
import ru.practicum.ewmmain.model.Category;
import ru.practicum.ewmmain.model.Event;
import ru.practicum.ewmmain.model.Location;

@Mapper
public interface EventMapper {
    EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventDate", source = "dto.eventTimestamp")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    Event fromDto(NewEventDto dto, Category category, Location location);

    @Mapping(target = "confirmedRequests", source = "confirmedRequests")
    EventFullDto toFullDto(Event event, Long confirmedRequests);

    EventShortDto toShortDto(Event event);
}
