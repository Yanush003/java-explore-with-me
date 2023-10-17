package ru.practicum.ewmmain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewmmain.constant.ParticipationRequestStatus;

import java.time.LocalDateTime;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequestDto { //description: Заявка на участие в событии
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime created; //Дата и время создания заявки
    private Long event; //Идентификатор события
    private Long id;
    private Long requester; //Идентификатор пользователя, отправившего заявку
    private ParticipationRequestStatus status; //Статус заявки
}
