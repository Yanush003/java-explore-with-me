package ru.practicum.ewmmain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmmain.dto.CategoryDto;
import ru.practicum.ewmmain.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class PublicCategoriesController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAll(@Valid @RequestParam(defaultValue = "0") @Min(0) int from,
                                    @Valid @RequestParam(defaultValue = "10") @Min(1) int size) {
        return categoryService.getAll(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getById(@PathVariable Long catId) {
        return categoryService.getById(catId);
    }
}
