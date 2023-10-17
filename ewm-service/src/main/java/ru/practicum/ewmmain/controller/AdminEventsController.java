package ru.practicum.ewmmain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmmain.dto.EventFullDto;
import ru.practicum.ewmmain.constant.EventState;
import ru.practicum.ewmmain.dto.UpdateEventAdminRequest;
import ru.practicum.ewmmain.service.AdminEventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;


@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventsController {
    private final AdminEventService adminEventService;

    @GetMapping
    public List<EventFullDto> getAll(@RequestParam(required = false) List<Long> users,
                                     @RequestParam(required = false) List<EventState> states,
                                     @RequestParam(required = false) List<Long> categories,
                                     @RequestParam(required = false)
                                         @DateTimeFormat(pattern = DATETIME_FORMAT) LocalDateTime rangeStart,
                                     @RequestParam(required = false)
                                         @DateTimeFormat(pattern = DATETIME_FORMAT) LocalDateTime rangeEnd,
                                     @Valid @RequestParam(defaultValue = "0") @Min(0) int from,
                                     @Valid @RequestParam(defaultValue = "10") @Min(1) int size) {
        return adminEventService.getAllAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto update(@PathVariable Long eventId,
                               @Valid @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return adminEventService.updateAdmin(eventId, updateEventAdminRequest);
    }
}
