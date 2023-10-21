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

    @Column(name = "confirmed_requests")
    private Integer confirmedRequests;

    @Column(name = "created_on")
    private String createdOn;

    private String description;

    @Column(name = "event_date")
    private String eventDate;

    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "initiator_id")
    private UserShort initiator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    private Boolean paid;

    @Column(name = "participant_limit")
    private Integer participantLimit;

    @Column(name = "published_on")
    private String publishedOn;

    @Column(name = "request_moderation")
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
