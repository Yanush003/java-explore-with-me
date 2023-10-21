package ru.practicum.ewmmain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmmain.dto.ParticipationRequestDto;
import ru.practicum.ewmmain.service.ParticipationRequestService;

import java.util.List;


@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
public class PrivateParticipationRequestsController {
    private final ParticipationRequestService participationRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto create(@PathVariable Long userId, @RequestParam Long eventId) {
        return participationRequestService.create(userId, eventId);
    }

    @GetMapping
    public List<ParticipationRequestDto> getAll(@PathVariable Long userId) {
        return participationRequestService.getAllRequests(userId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto update(@PathVariable Long userId, @PathVariable Long requestId) {
        return participationRequestService.update(userId, requestId);
    }
}
