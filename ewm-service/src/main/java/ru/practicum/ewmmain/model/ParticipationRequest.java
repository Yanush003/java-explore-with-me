package ru.practicum.ewmmain.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmmain.constant.ParticipationRequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "participation_requests")
public class ParticipationRequest {//description: Заявка на участие в событии

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_request_id")
    private Long id;

    @Column(name = "participation_request_created")
    private LocalDateTime created; //Дата и время создания заявки

    @ManyToOne
    @JoinColumn(name = "participation_request_event_id", nullable = false)
    private Event event; //Идентификатор события

    @ManyToOne
    @JoinColumn(name = "participation_request_requester_id", nullable = false)
    private User requester;

    @Enumerated(EnumType.STRING)
    @Column(name = "participation_request_status")
    private ParticipationRequestStatus status; //Статус заявки
}
