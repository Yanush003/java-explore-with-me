package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmmain.dto.CategoryDto;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.model.Category;
import ru.practicum.ewmmain.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.mapper.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto create(CategoryDto dto) {
        return CATEGORY_MAPPER.toDto(categoryRepository.save(CATEGORY_MAPPER.fromDto(dto)));
    }

    public List<CategoryDto> getAll(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size)).getContent().stream()
                .map(CATEGORY_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto getById(Long catId) {
        return CATEGORY_MAPPER.toDto(categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Category id=" + catId + " not found.")));
    }

    @Transactional
    public CategoryDto update(Long catId, CategoryDto dto) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Category id=" + catId + " not found."));
        Optional.ofNullable(dto.getName()).ifPresent(category::setName);
        return CATEGORY_MAPPER.toDto(categoryRepository.save(category));
    }

    @Transactional
    public void delete(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            throw new NotFoundException("Category id=" + catId + " not found.");
        }
        categoryRepository.deleteById(catId);
    }
}
