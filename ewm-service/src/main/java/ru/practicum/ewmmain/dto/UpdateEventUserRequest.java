package ru.practicum.ewmmain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewmmain.constant.StateActionUser;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest { //description: Данные для изменения информации о событии. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    @Size(min = 20, max = 2000)
    private String annotation; //Новая аннотация
    private Long category; //Новая категория
    @Size(min = 20, max = 7000)
    private String description; //Новое описание
    @Future(message = "Error! Event date must be in future.")
    @JsonProperty("eventDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime eventTimestamp; //Новые дата и время на которые намечено событие. Дата и время указываются в формате "yyyy-MM-dd HH:mm:ss"
    private LocationDto location;
    private Boolean paid; //Новое значение флага о платности мероприятия
    private Integer participantLimit; //Новый лимит пользователей
    private Boolean requestModeration; //Нужна ли пре-модерация заявок на участие
    private StateActionUser stateAction; //Новое состояние события (Enum)
    @Size(min = 3, max = 120)
    private String title; //Новый заголовок
}
