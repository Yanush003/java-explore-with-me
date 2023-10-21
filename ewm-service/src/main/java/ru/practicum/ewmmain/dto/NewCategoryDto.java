package ru.practicum.ewmmain.dto;

import javax.validation.constraints.Size;

public class NewCategoryDto { //description: Данные для добавления новой категории
    @Size(min = 1, max = 50)
    private String name; //Название категории
}
