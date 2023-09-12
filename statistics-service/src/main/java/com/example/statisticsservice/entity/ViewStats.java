package com.example.statisticsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app")
    private String app;

    @Column(name = "uri")
    private String uri;

    @Column(name = "hits")
    private Integer hits;

    @Column(name = "date")
    private LocalDate date;
}

