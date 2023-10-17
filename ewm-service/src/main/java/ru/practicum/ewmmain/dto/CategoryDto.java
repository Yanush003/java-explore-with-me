package ru.practicum.ewmmain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto { //description: Категория

    private Integer id;
    @Size(min = 1, max = 50)
    private String name;
}
