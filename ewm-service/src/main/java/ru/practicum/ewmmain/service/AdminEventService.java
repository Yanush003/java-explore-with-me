package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pracitcum.dto.ViewStatsDto;
import ru.practicum.client.StatsClient;
import ru.practicum.ewmmain.constant.EventState;
import ru.practicum.ewmmain.dto.EventFullDto;
import ru.practicum.ewmmain.dto.LocationDto;
import ru.practicum.ewmmain.dto.UpdateEventAdminRequest;
import ru.practicum.ewmmain.exception.ImpossibleOperationException;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.exception.WrongDataException;
import ru.practicum.ewmmain.model.Event;
import ru.practicum.ewmmain.model.Location;
import ru.practicum.ewmmain.repository.CategoryRepository;
import ru.practicum.ewmmain.repository.EventRepository;
import ru.practicum.ewmmain.repository.LocationRepository;
import ru.practicum.ewmmain.repository.ParticipationRequestRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;
import static ru.practicum.ewmmain.constant.ParticipationRequestStatus.CONFIRMED;
import static ru.practicum.ewmmain.constant.StateActionAdmin.PUBLISH_EVENT;
import static ru.practicum.ewmmain.constant.StateActionAdmin.REJECT_EVENT;
import static ru.practicum.ewmmain.mapper.EventMapper.EVENT_MAPPER;
import static ru.practicum.ewmmain.mapper.LocationMapper.LOCATION_MAPPER;

@Service
@RequiredArgsConstructor
public class AdminEventService {
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;
    private final ParticipationRequestRepository participationRequestRepository;
    private final StatsClient statsClient;

    public List<EventFullDto> getAllAdmin(List<Long> users, List<EventState> states, List<Long> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        if (rangeStart == null) {
            rangeStart = LocalDateTime.now();
        }
        if (rangeEnd == null) {
            rangeEnd = LocalDateTime.now().plusYears(100);
        }
        List<Event> events = eventRepository.findAllByAdmin(users, states, categories, rangeStart, rangeEnd, pageable);
        List<String> eventUrls = events.stream().map(event -> "/events/" + event.getId()).collect(Collectors.toList());
        List<ViewStatsDto> viewStatsDtos = statsClient.getStatistic(
                rangeStart.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)),
                rangeEnd.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)),
                eventUrls,
                true);
        return events.stream().map(event->EVENT_MAPPER.toFullDto(event, participationRequestRepository.countByEventIdAndStatus(event.getId(), CONFIRMED))).peek(eventFullDto -> {
            Optional<ViewStatsDto> viewStatsDto = viewStatsDtos.stream().filter(viewStatsDto1 -> viewStatsDto1.getUri().equals("/events/" + eventFullDto.getId())).findFirst();
            eventFullDto.setViews(viewStatsDto.map(ViewStatsDto::getHits).orElse(0L));
        }).peek(eventFullDto -> eventFullDto.setConfirmedRequests(participationRequestRepository.countByEventIdAndStatus(eventFullDto.getId(), CONFIRMED))).collect(Collectors.toList());
    }

    @Transactional
    public EventFullDto updateAdmin(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event id=" + eventId + " not found."));

        if (updateEventAdminRequest.getEventTimestamp() != null && LocalDateTime.now().plusHours(1).isAfter(updateEventAdminRequest.getEventTimestamp())) {
            throw new WrongDataException("The date and time for which the event is scheduled cannot be earlier than " + "one hour from the current moment.");
        }

        if (updateEventAdminRequest.getStateAction() != null) {

            if (updateEventAdminRequest.getStateAction().equals(PUBLISH_EVENT) && !event.getState().equals(EventState.PENDING)) {
                throw new ImpossibleOperationException(event.getState().toString());
            }
            if (updateEventAdminRequest.getStateAction().equals(REJECT_EVENT) && event.getState().equals(EventState.PUBLISHED)) {
                throw new ImpossibleOperationException(event.getState().toString());
            }
        }

        if (updateEventAdminRequest.getCategory() != null) {
            event.setCategory(categoryRepository.findById(updateEventAdminRequest.getCategory()).orElseThrow(
                    () -> new NotFoundException("Category id=" + updateEventAdminRequest.getCategory() + " not found.")));
        }

        if (updateEventAdminRequest.getLocation() != null) {
            event.setLocation(getLocation(updateEventAdminRequest.getLocation()));
        }

        Optional.ofNullable(updateEventAdminRequest.getTitle()).ifPresent(event::setTitle);
        Optional.ofNullable(updateEventAdminRequest.getAnnotation()).ifPresent(event::setAnnotation);
        Optional.ofNullable(updateEventAdminRequest.getDescription()).ifPresent(event::setDescription);
        Optional.ofNullable(updateEventAdminRequest.getEventTimestamp()).ifPresent(event::setEventDate);
        Optional.ofNullable(updateEventAdminRequest.getParticipantLimit()).ifPresent(event::setParticipantLimit);
        Optional.ofNullable(updateEventAdminRequest.getPaid()).ifPresent(event::setPaid);
        Optional.ofNullable(updateEventAdminRequest.getRequestModeration()).ifPresent(event::setRequestModeration);

        if (updateEventAdminRequest.getStateAction() != null) {
            switch (updateEventAdminRequest.getStateAction()) {
                case PUBLISH_EVENT:
                    event.setState(EventState.PUBLISHED);
                    event.setPublishedOn(LocalDateTime.now());
                    break;
                case REJECT_EVENT:
                    event.setState(EventState.CANCELED);
                    break;
            }
        }
        return EVENT_MAPPER.toFullDto(eventRepository.save(event), participationRequestRepository.countByEventIdAndStatus(event.getId(), CONFIRMED));
    }

    private Location getLocation(LocationDto locationDto) {
        Location location = locationRepository.findByLatAndLon(locationDto.getLat(), locationDto.getLon());
        return location != null ? location : locationRepository.save(LOCATION_MAPPER.fromDto(locationDto));
    }
}
