package ru.practicum.ewmmain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewmmain.constant.StateActionAdmin;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventAdminRequest { //description: Данные для изменения информации о событии. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.

    @Size(min = 20, max = 2000)
    private String annotation; //Новая аннотация
    private Long category; //Новая категория
    @Size(min = 20, max = 7000)
    private String description; //Новое описание
    @Future
    @JsonProperty("eventDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime eventTimestamp;
    private LocationDto location;
    private Boolean paid; //Новое значение флага о платности мероприятия
    private Integer participantLimit; //Новый лимит пользователей
    private Boolean requestModeration; //Нужна ли пре-модерация заявок на участие
    private StateActionAdmin stateAction; //Новое состояние события (Enum)
    @Size(min = 3, max = 120)
    private String title; //Новый заголовок

}
