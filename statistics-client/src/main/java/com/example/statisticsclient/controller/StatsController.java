package com.example.statisticsclient.controller;

import com.example.commondto.dto.EndpointHitDto;
import com.example.commondto.dto.ViewStatsDto;
import com.example.statisticsservice.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/hit")
    public ResponseEntity<Void> registerHit(@RequestBody EndpointHitDto hit) {
        statsService.registerHit(hit);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStatsDto>> getStats(
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") boolean unique) {
        List<ViewStatsDto> stats = statsService.getStats(start, end, uris, unique);
        return ResponseEntity.ok(stats);
    }
}
