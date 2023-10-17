package ru.practicum.ewmmain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewmmain.dto.CompilationDto;
import ru.practicum.ewmmain.dto.NewCompilationDto;
import ru.practicum.ewmmain.model.Compilation;
import ru.practicum.ewmmain.model.Event;

import java.util.List;

@Mapper(uses = EventMapper.class)
public interface CompilationMapper {
    CompilationMapper COMPILATION_MAPPER = Mappers.getMapper(CompilationMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    Compilation fromDto(NewCompilationDto dto, List<Event> events);

    CompilationDto toDto(Compilation compilation);
}
