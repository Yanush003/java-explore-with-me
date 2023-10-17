package ru.practicum.ewmmain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto { //description: Подборка событий
    private Integer id;
    private List<EventShortDto> events; //Список событий входящих в подборку
    private Boolean pinned; //Закреплена ли подборка на главной странице сайта
    private String title; //Заголовок подборки
}
