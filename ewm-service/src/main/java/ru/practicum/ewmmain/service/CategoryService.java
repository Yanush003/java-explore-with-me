package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmmain.dto.CategoryDto;
import ru.practicum.ewmmain.exception.ImpossibleOperationException;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.model.Category;
import ru.practicum.ewmmain.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.mapper.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PublicEventService publicEventService;

    @Transactional
    public CategoryDto create(CategoryDto dto) {
        if (categoryRepository.isNameExisting(null, dto.getName())) {
            throw new ImpossibleOperationException("Category with name=" + dto.getName() + " exists.");
        }
        return CATEGORY_MAPPER.toDto(categoryRepository.save(CATEGORY_MAPPER.fromDto(dto)));
    }

    public List<CategoryDto> getAll(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size)).getContent().stream()
                .map(CATEGORY_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getById(Long catId) {
        return CATEGORY_MAPPER.toDto(categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Category id=" + catId + " not found.")));
    }

    @Transactional
    public CategoryDto update(Long catId, CategoryDto dto) {
        if (categoryRepository.isNameExisting(catId, dto.getName())) {
            throw new ImpossibleOperationException("Category with name=" + dto.getName() + " exists.");
        }
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
        if (publicEventService.isExistingCategoryId(catId)) {
            throw new ImpossibleOperationException("");
        }
        categoryRepository.deleteById(catId);
    }
}
