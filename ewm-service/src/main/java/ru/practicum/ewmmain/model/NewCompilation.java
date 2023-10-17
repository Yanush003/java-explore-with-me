package ru.practicum.ewmmain.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.List;

public class NewCompilation {//description: Подборка событий

    @ManyToOne
    private List<Integer> events; //Список идентификаторов событий входящих в подборку
    private Boolean pinned; //default: false Закреплена ли подборка на главной странице сайта
    @Size(min = 1, max = 50)
    private String title; //Заголовок подборки
}
