package ru.practicum.ewmmain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewmmain.dto.ApiError;

import java.time.LocalDateTime;


@RestControllerAdvice
@RequiredArgsConstructor
public class MyExceptionHandler {
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiError> handlerDuplicateEmailException(InternalServerErrorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .reason("InternalServerErrorException")
                        .message(ex.getClass() + " - " + ex.getMessage())
                        .errors(ex.getStackTrace())
                        .errorTimestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ImpossibleOperationException.class)
    public ResponseEntity<ApiError> handleImpossibleOperationException(ImpossibleOperationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiError.builder()
                        .status(HttpStatus.CONFLICT)
                        .reason("ExceptionConflict")
                        .message(ex.getMessage())
                        .errorTimestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handlerIllegalArgumentException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .reason("BadRequestException")
                        .message(ex.getMessage())
                        .errorTimestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handlerIllegalArgumentException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .reason("NotFoundException")
                        .message(ex.getMessage())
                        .errorTimestamp(LocalDateTime.now())
                        .build());
    }
}
