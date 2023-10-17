package ru.practicum.ewmmain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCompilationDto { //description: Подборка событий
    private List<Long> events; //Список идентификаторов событий входящих в подборку
    private Boolean pinned; //default: false Закреплена ли подборка на главной странице сайта
    @Size(min = 1, max = 50)
    private String title; //Заголовок подборки
}
