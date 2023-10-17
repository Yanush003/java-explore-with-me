package ru.practicum.ewmmain.model;

import javax.validation.constraints.Size;

public class NewCategory {//description: Данные для добавления новой категории

    @Size(min = 1, max = 50)
    private String name; //Название категории
}
