package ru.practicum.ewmmain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmmain.dto.CommentDto;
import ru.practicum.ewmmain.dto.NewCommentDto;
import ru.practicum.ewmmain.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
public class PrivateCommentController {
    private final CommentService commentService;

    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@PathVariable Long userId, @PathVariable Long eventId,
                             @Valid @RequestBody NewCommentDto dto) {
        return commentService.create(userId, eventId, dto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto patch(@PathVariable Long userId,
                            @PathVariable Long commentId,
                            @Valid @RequestBody NewCommentDto dto) {
        return commentService.update(userId, commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deletePrivate(userId, commentId);
    }
}
