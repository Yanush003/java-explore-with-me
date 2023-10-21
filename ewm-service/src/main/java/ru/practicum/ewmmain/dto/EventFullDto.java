package ru.practicum.ewmmain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewmmain.constant.EventState;

import java.time.LocalDateTime;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventFullDto {
    private Long id;
    private String annotation; //Краткое описание
    private CategoryDto category;
    private Long confirmedRequests; //Количество одобренных заявок на участие в данном событии
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime createdOn; //Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
    private String description; //Полное описание события
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")

    private UserShortDto initiator;
    private LocationDto location;
    private Boolean paid; //Нужно ли оплачивать участие
    private Integer participantLimit; //default: 0
    //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
    private Boolean requestModeration; //default: true
    //Нужна ли пре-модерация заявок на участие
    private EventState state; //Список состояний жизненного цикла события (Enum)
    private String title; //Заголовок
    private Long views; //Количество просмотрев события

}
