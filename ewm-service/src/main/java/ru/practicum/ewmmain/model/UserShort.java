package ru.practicum.ewmmain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_short")
public class UserShort {
    @Id
    private Integer id;

    private String name;
}
