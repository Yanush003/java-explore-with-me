package com.example.commondto.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndpointHitDto {
    private Long id;  // Идентификатор записи. Обратите внимание на то, что это поле только для чтения.
    private String app;  // Идентификатор сервиса
    private String uri;  // URI для которого был осуществлен запрос
    private String ip;  // IP-адрес пользователя
    private String timestamp;  // Дата и время запроса
}
