package ru.practicum.ewmmain.model;

import javax.validation.constraints.Size;

public class NewCategory {
    @Size(min = 1, max = 50)
    private String name;
}
