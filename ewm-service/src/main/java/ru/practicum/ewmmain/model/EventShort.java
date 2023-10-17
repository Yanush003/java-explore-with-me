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
public class EventShort {//description:	Краткая информация о событии
    private String annotation; //Краткое описание
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии
    private String eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private UserShort initiator;
    private Boolean paid; //Нужно ли оплачивать участие
    private String title; //заголовок
    private Integer views; //Количество просмотрев события
}
