package ru.practicum.ewmmain.dto;

import javax.validation.constraints.Size;

public class NewUserRequest { //description: Данные нового пользователя
    @Size(min = 6, max = 254)
    private String email;
    @Size(min = 2, max = 250)
    private String name;
}
