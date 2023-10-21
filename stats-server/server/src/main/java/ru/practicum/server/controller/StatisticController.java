package ru.practicum.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pracitcum.dto.EndpointHitDto;
import ru.pracitcum.dto.ViewStatsDto;
import ru.practicum.server.exception.InvalidDateException;
import ru.practicum.server.service.StatisticService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class StatisticController {

    private final StatisticService statsService;

    @PostMapping("/hit")
    public ResponseEntity<EndpointHitDto> registerHit(@RequestBody EndpointHitDto hit) {
        EndpointHitDto endpointHitDto = statsService.registerHit(hit);
        return ResponseEntity.status(201).body(endpointHitDto);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStatsDto>> getStats(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") boolean unique) {
        if (start.isAfter(end)) {
            throw new InvalidDateException(" ");
        }
        List<ViewStatsDto> stats = statsService.getStatList(start, end, uris, unique);
        return ResponseEntity.ok(stats);
    }
}
