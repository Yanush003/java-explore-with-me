package ru.practicum.ewmmain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compilations")
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title; //Заголовок подборки

    @Column(name = "pinned")
    private Boolean pinned; //Закреплена ли подборка на главной странице сайта

    @ManyToMany
    @JoinTable(name = "compilations_events", joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;
}
