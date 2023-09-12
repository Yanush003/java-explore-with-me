package com.example.commondto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewStatsDto {
    private String app;  // Название сервиса
    private String uri;  // URI сервиса
    private Integer hits;  // Количество просмотров
}
