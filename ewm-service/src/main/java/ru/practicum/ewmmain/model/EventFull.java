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
    private String annotation; //Краткое описание
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    private Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии
    private String createdOn; //Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
    private String description; //Полное описание события
    private String eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    @Id
    private Integer id;
    @OneToOne
    @JoinColumn(name = "initiator_id")
    private UserShort initiator;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;
    @Enumerated(EnumType.STRING)
    private Boolean paid; //Нужно ли оплачивать участие
    private Integer participantLimit; //default: 0
    //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    private String publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
    private Boolean requestModeration; //default: true
    //Нужна ли пре-модерация заявок на участие
    private String state; //Список состояний жизненного цикла события (Enum)
    private String title; //Заголовок
    private Integer views; //Количество просмотрев события

    public enum EnumState {
        PENDING,
        PUBLISHED,
        CANCELED
    }
}
