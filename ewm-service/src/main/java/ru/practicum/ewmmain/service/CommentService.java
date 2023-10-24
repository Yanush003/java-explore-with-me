package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmmain.dto.CommentDto;
import ru.practicum.ewmmain.dto.NewCommentDto;
import ru.practicum.ewmmain.exception.ForbiddenException;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.model.Comment;
import ru.practicum.ewmmain.model.Event;
import ru.practicum.ewmmain.model.User;
import ru.practicum.ewmmain.repository.CommentRepository;
import ru.practicum.ewmmain.repository.EventRepository;
import ru.practicum.ewmmain.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.mapper.CommentMapper.COMMENT_MAPPER;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Transactional
    public CommentDto create(Long userId, Long eventId, NewCommentDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id=" + userId + " not found."));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event id=" + eventId + " not found."));
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setEvent(event);
        comment.setText(dto.getText());
        comment.setCreated(LocalDateTime.now());

        return COMMENT_MAPPER.toDto(commentRepository.save(comment));
    }

    public List<CommentDto> getAllByEventId(Long eventId) {
        return commentRepository.findAllByEventId(eventId).stream()
                .map(COMMENT_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto update(Long userId, Long commentId, NewCommentDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment id=" + commentId + " not found."));
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new ForbiddenException("User id=" + userId + " is not author of comment id=" + commentId);
        }
        if (comment.getCreated().plusHours(24).isBefore(LocalDateTime.now())) {
            throw new ForbiddenException("");
        }
        Optional.ofNullable(dto.getText()).ifPresent(comment::setText);

        return COMMENT_MAPPER.toDto(commentRepository.save(comment));
    }

    @Transactional
    public void deletePrivate(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment id=" + commentId + " not found."));
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new ForbiddenException("User id=" + userId + " is not author of comment id=" + commentId);
        }
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void deleteAdmin(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new NotFoundException("Comment id=" + commentId + " not found.");
        }
        commentRepository.deleteById(commentId);
    }
}
