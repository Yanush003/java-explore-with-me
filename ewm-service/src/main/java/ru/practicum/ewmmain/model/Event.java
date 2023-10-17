package ru.practicum.ewmmain.model;

import lombok.*;
import ru.practicum.ewmmain.constant.EventState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_annotation", nullable = false)
    private String annotation; //Краткое описание

    @ManyToOne
    @JoinColumn(name = "event_category_id", nullable = false)
    private Category category;

    @Column(name = "event_confirmed_requests")
    private Long confirmedRequests; //Количество одобренных заявок на участие в данном событии

    @Column(name = "event_created_on")
    private LocalDateTime createdOn; //Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "event_description", nullable = false)
    private String description; //Полное описание события

    @Column(name = "event_event_date", nullable = false)
    private LocalDateTime  eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_initiator_id", nullable = false)
    private User initiator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_location_id", nullable = false)
    private Location location;

    @Column(name = "event_paid", nullable = false)
    private Boolean paid; //Нужно ли оплачивать участие

    @Column(name = "event_participant_limit", nullable = false)
    private Integer participantLimit; //default: 0
    //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    @Column(name = "event_published_on")
    private LocalDateTime  publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "event_request_moderation")
    private Boolean requestModeration; //default: true //Нужна ли пре-модерация заявок на участие

    @Enumerated(EnumType.STRING)
    @Column(name = "event_state", nullable = false)
    private EventState state; //Список состояний жизненного цикла события (Enum)

    @Column(name = "event_title", nullable = false)
    private String title; //Заголовок
}
