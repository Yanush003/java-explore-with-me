package ru.practicum.ewmmain.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.practicum.client.StatsClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public StatsClient statsClient(@Value("${stats.server.url}") String serverUrl) {
        return new StatsClient(serverUrl);
    }
}
