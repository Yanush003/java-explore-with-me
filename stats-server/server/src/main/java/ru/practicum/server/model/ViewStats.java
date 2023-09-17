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
    public ViewStats(String app, String uri, Long count) {
        this.app = app;
        this.uri = uri;
        this.count = count;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String app;
    private String uri;
    private Long count;
}
