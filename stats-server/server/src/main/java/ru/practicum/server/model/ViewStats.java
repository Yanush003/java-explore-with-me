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
    public ViewStats(String app, String uri, Long hits) {
        this.app = app;
        this.uri = uri;
        this.hits = hits;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String app;
    private String uri;
    private Long hits;
}
