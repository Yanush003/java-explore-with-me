package ru.practicum.ewmmain.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "event_short")
public class EventShort {
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "confirmed_requests")
    private Integer confirmedRequests;

    @Column(name = "event_date")
    private String eventDate;

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private UserShort initiator;

    private Boolean paid;

    private String title;

    private Integer views;
}
