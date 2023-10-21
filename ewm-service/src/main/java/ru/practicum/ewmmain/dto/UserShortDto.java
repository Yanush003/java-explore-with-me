package ru.practicum.ewmmain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto { //description: Пользователь (краткая информация)
    private Integer id;
    private String name;
}
