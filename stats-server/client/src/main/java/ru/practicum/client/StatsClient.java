package ru.practicum.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.pracitcum.dto.EndpointHitDto;
import ru.pracitcum.dto.ViewStatsDto;

import java.util.List;
@Service
public class StatsClient {
    private final WebClient webClient;

    public StatsClient(@Value("${stats.server.url}") String serverUrl) {
        webClient = WebClient.builder().baseUrl(serverUrl).build();
    }

    public void createHit(EndpointHitDto endpointHitDto) {
        webClient.post()
                .uri("/hit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(endpointHitDto))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public List<ViewStatsDto> getStatistic(String start, String end, List<String> uris, Boolean unique) {
        String urisAsSting = String.join(",", uris);

        return webClient.get()
                .uri("/stats?start={start}&end={end}&uris={uris}&unique={unique}", start, end, urisAsSting, unique)
                .retrieve()
                .bodyToFlux(ViewStatsDto.class)
                .collectList()
                .block();
    }
}
