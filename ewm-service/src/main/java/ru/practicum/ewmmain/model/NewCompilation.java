package ru.practicum.ewmmain.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.List;

public class NewCompilation {
    @ManyToOne
    private List<Integer> events;

    private Boolean pinned;

    @Size(min = 1, max = 50)
    private String title;
}
