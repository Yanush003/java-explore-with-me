package ru.practicum.ewmmain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewmmain.constant.EventRequestStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestStatusUpdateRequest { //description: Изменение статуса запроса на участие в событии текущего пользователя
    @NotNull
    private List<Long> requestIds; //Идентификаторы запросов на участие в событии текущего пользователя
    @NotNull
    private EventRequestStatus status; //Новый статус запроса на участие в событии текущего пользователя (Enum)

}
