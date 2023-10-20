package ru.practicum.ewmmain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmmain.constant.SortMode;
import ru.practicum.ewmmain.dto.EventFullDto;
import ru.practicum.ewmmain.dto.EventShortDto;
import ru.practicum.ewmmain.service.PublicEventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;


@RestController
@Slf4j
@RequestMapping("/events")
@RequiredArgsConstructor
public class PublicEventsController {
    private final PublicEventService publicEventService;

    @GetMapping
    public List<EventShortDto> getAll(@RequestParam(defaultValue = "") String text,
                                      @RequestParam(required = false) List<Long> categories,
                                      @RequestParam(required = false) Boolean paid,
                                      @RequestParam(required = false)
                                      @DateTimeFormat(pattern = DATETIME_FORMAT) LocalDateTime rangeStart,
                                      @RequestParam(required = false)
                                      @DateTimeFormat(pattern = DATETIME_FORMAT) LocalDateTime rangeEnd,
                                      @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                      @RequestParam(defaultValue = "VIEWS") SortMode sort,
                                      @Valid @RequestParam(defaultValue = "0") @Min(0) int from,
                                      @Valid @RequestParam(defaultValue = "10") @Min(1) int size,
                                      HttpServletRequest request) {
        log.info("/events success");
        return publicEventService.getAllPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getById(@PathVariable("eventId") Long eventId, HttpServletRequest request) {
        return publicEventService.getByIdPublic(eventId, request);
    }
}
