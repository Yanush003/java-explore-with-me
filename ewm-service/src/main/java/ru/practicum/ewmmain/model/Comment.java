package ru.practicum.ewmmain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "comment_event_id", nullable = false)
    private Event event;

    @Column(name = "comment_text", nullable = false)
    private String text;

    @Column(name = "comment_created")
    private LocalDateTime created;
}
