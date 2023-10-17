package ru.practicum.ewmmain.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "event_full")
public class EventFull {
    private String annotation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer confirmedRequests;

    private String createdOn;

    private String description;

    private String eventDate;

    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "initiator_id")
    private UserShort initiator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @Enumerated(EnumType.STRING)
    private Boolean paid;

    private Integer participantLimit;

    private String publishedOn;

    private Boolean requestModeration;

    private String state;

    private String title;

    private Integer views;

    public enum EnumState {
        PENDING,
        PUBLISHED,
        CANCELED
    }
}
