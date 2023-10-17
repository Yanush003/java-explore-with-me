package ru.practicum.ewmmain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompilationRequest { //description: Изменение информации о подборке событий. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    private Set<Long> events; //uniqueItems: true Список id событий подборки для полной замены текущего списка
    private Boolean pinned; //Закреплена ли подборка на главной странице сайта
    @Size(min = 1, max = 50)
    private String title; // Заголовок подборки
}
