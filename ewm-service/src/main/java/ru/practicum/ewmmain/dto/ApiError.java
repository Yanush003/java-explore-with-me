package ru.practicum.ewmmain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static ru.practicum.ewmmain.constant.Constant.DATETIME_FORMAT;


@Data
@Builder
public class ApiError { //description: Сведения об ошибке
    private StackTraceElement[] errors; //Список стектрейсов или описания ошибок
    private String message; //Сообщение об ошибке
    private String reason; //Общее описание причины ошибки
    private HttpStatus status; //Код статуса HTTP-ответа
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime errorTimestamp; //Дата и время когда произошла ошибка (в формате "yyyy-MM-dd HH:mm:ss")
}
