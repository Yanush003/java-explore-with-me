package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmmain.dto.CompilationDto;
import ru.practicum.ewmmain.dto.NewCompilationDto;
import ru.practicum.ewmmain.dto.UpdateCompilationRequest;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.model.Compilation;
import ru.practicum.ewmmain.model.Event;
import ru.practicum.ewmmain.repository.CompilationRepository;
import ru.practicum.ewmmain.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.mapper.CompilationMapper.COMPILATION_MAPPER;

@Service
@RequiredArgsConstructor
public class CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    @Transactional
    public CompilationDto create(NewCompilationDto dto) {
        List<Event> events = dto.getEvents() != null && !dto.getEvents().isEmpty() ?
                eventRepository.findAllById(dto.getEvents()) : new ArrayList<>();
        if (dto.getPinned() == null) {
            dto.setPinned(false);
        }

        return COMPILATION_MAPPER.toDto(compilationRepository.save(COMPILATION_MAPPER.fromDto(dto, events)));
    }

    @Transactional(readOnly = true)
    public List<CompilationDto> getAll(Boolean pinned, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return compilationRepository.findAllByPinnedIsNullOrPinned(pinned, pageable).stream()
                .map(COMPILATION_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompilationDto getById(Long compId) {
        return COMPILATION_MAPPER.toDto(compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Compilation id=" + compId + " not found.")));
    }

    @Transactional
    public CompilationDto update(Long compId, UpdateCompilationRequest compilationRequest) {
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Compilation id=" + compId + " not found."));

        if (compilationRequest.getEvents() != null) {
            compilation.setEvents(eventRepository.findAllById(compilationRequest.getEvents()));
        }

        Optional.ofNullable(compilationRequest.getTitle()).ifPresent(compilation::setTitle);
        Optional.ofNullable(compilationRequest.getPinned()).ifPresent(compilation::setPinned);

        return COMPILATION_MAPPER.toDto(compilation);
    }

    @Transactional
    public void delete(Long compId) {
        if (!compilationRepository.existsById(compId)) {
            throw new NotFoundException("Compilation id=" + compId + " not found.");
        }
        compilationRepository.deleteById(compId);
    }
}
