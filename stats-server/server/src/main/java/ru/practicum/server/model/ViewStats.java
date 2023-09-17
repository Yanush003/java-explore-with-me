package ru.practicum.server.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "view_stats")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ViewStats {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String app;
    private String uri;
    private Long hits;
}
