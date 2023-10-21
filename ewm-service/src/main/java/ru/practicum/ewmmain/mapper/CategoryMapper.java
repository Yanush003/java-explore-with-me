package ru.practicum.ewmmain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewmmain.dto.CategoryDto;
import ru.practicum.ewmmain.model.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category category);

    Category fromDto(CategoryDto dto);
}
