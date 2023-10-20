package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewmmain.dto.ParticipationRequestDto;
import ru.practicum.ewmmain.exception.ImpossibleOperationException;
import ru.practicum.ewmmain.exception.InternalServerErrorException;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.model.Event;
import ru.practicum.ewmmain.model.ParticipationRequest;
import ru.practicum.ewmmain.model.User;
import ru.practicum.ewmmain.repository.EventRepository;
import ru.practicum.ewmmain.repository.ParticipationRequestRepository;
import ru.practicum.ewmmain.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.constant.EventState.PUBLISHED;
import static ru.practicum.ewmmain.constant.ParticipationRequestStatus.*;
import static ru.practicum.ewmmain.mapper.ParticipationRequestMapper.REQUEST_MAPPER;

@Service
@RequiredArgsConstructor
public class ParticipationRequestService {
    private final ParticipationRequestRepository participationRequestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public ParticipationRequestDto create(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id=" + userId + " not found."));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event id=" + eventId + " not found."));
        if (event.getInitiator().getId().equals(userId)) {
            throw new ImpossibleOperationException("");
        }
        if (!event.getState().equals(PUBLISHED)) {
            throw new ImpossibleOperationException("");
        }
        if (event.getParticipantLimit() > 0) {
            if (event.getParticipantLimit() <= participationRequestRepository
                    .countByEventIdAndStatus(eventId, CONFIRMED)) {
                throw new ImpossibleOperationException("");
            }
        }
        if (participationRequestRepository.findByRequester_IdAndEvent_Id(userId, eventId) != null) {
            throw new ImpossibleOperationException("");
        }
        ParticipationRequest participationRequest = new ParticipationRequest();
        participationRequest.setCreated(LocalDateTime.now());
        participationRequest.setEvent(event);
        participationRequest.setRequester(user);
        participationRequest.setStatus(
                event.getRequestModeration() && !event.getParticipantLimit().equals(0) ? PENDING : CONFIRMED);
        return REQUEST_MAPPER.toDto(participationRequestRepository.save(participationRequest));
    }


    public List<ParticipationRequestDto> getAllRequests(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User id=" + userId + "not found.");
        }
        return participationRequestRepository.findAllByRequesterId(userId).stream()
                .map(REQUEST_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    public ParticipationRequestDto update(Long userId, Long requestId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User id=" + userId + "not found.");
        }
        ParticipationRequest request = participationRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Participation request id=" + requestId + " not found."));
        if (!request.getRequester().getId().equals(userId)) {
            throw new NotFoundException("Request id=" + requestId + " not found.");
        }
        if (request.getStatus().equals(CONFIRMED)) {
            throw new InternalServerErrorException("");
        }
        request.setStatus(CANCELED);
        return REQUEST_MAPPER.toDto(participationRequestRepository.save(request));
    }
}
