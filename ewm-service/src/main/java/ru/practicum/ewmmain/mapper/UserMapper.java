package ru.practicum.ewmmain.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewmmain.dto.UserDto;
import ru.practicum.ewmmain.model.User;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User fromNewRequest(UserDto userDto);
}
