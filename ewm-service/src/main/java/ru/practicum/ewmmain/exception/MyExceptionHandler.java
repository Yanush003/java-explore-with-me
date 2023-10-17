package ru.practicum.ewmmain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewmmain.dto.ApiError;

import java.time.LocalDateTime;


@RestControllerAdvice
@RequiredArgsConstructor
public class MyExceptionHandler {
    @ExceptionHandler(InternalServerErrorException.class)
    public ApiError handlerDuplicateEmailException(InternalServerErrorException ex) {
        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason("InternalServerErrorException")
                .message(ex.getClass() + " - " + ex.getMessage())
                .errors(ex.getStackTrace())
                .errorTimestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ApiError handlerIllegalArgumentException(BadRequestException ex) {
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .reason("BadRequestException")
                .message(ex.getMessage())
                .errorTimestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ApiError handlerIllegalArgumentException(NotFoundException ex) {
        return ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .reason("NotFoundException")
                .message(ex.getMessage())
                .errorTimestamp(LocalDateTime.now())
                .build();
    }
}
