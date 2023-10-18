package ru.practicum.ewmmain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "new_event")
public class NewEvent {
    @Id
    private Integer id;

    @Size(min = 20, max = 2000)
    private String annotation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Size(min = 20, max = 7000)
    private String description;

    @Column(name = "event_date")
    private String eventDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private Boolean paid;

    @Column(name = "participant_limit")
    private Integer participantLimit;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @Size(min = 3, max = 120)
    private String title;
}
