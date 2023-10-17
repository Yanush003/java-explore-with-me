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
public class NewEvent {//description: Новое событие

    @Id
    private Integer id;
    @Size(min = 20, max = 2000)
    private String annotation; //Краткое описание события
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category; //id категории к которой относится событие
    @Size(min = 20, max = 7000)
    private String description; //Полное описание события
    private String eventDate; //Дата и время на которые намечено событие. Дата и время указываются в формате "yyyy-MM-dd HH:mm:ss"
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private Boolean paid; //Нужно ли оплачивать участие в событии
    private Integer participantLimit; //default: 0 Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    private Boolean requestModeration; // default: true Нужна ли пре-модерация заявок на участие. Если true, то все заявки будут ожидать подтверждения инициатором события. Если false - то будут подтверждаться автоматически.
    @Size(min = 3, max = 120)
    private String title; //Заголовок события
}
