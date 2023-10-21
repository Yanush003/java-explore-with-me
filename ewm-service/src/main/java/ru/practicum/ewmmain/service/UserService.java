package ru.practicum.ewmmain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmmain.dto.UserDto;
import ru.practicum.ewmmain.exception.ImpossibleOperationException;
import ru.practicum.ewmmain.exception.NotFoundException;
import ru.practicum.ewmmain.model.User;
import ru.practicum.ewmmain.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewmmain.mapper.UserMapper.USER_MAPPER;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto create(UserDto request) {
        if (userRepository.isNameExisting(request.getName())) {
            throw new ImpossibleOperationException("User with name=" + request.getName() + " exists.");
        }
        return USER_MAPPER.toDto(userRepository.save(USER_MAPPER.fromNewRequest(request)));
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll(List<Long> ids, int from, int size) {
        Pageable pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Page<User> users;
        if (ids != null && !ids.isEmpty()) {
            users = userRepository.findAllByIdIn(ids, pageable);
        } else {
            users = userRepository.findAll(pageable);
        }
        return users.getContent().stream()
                .map(USER_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User id=" + userId + "not found.");
        }
        userRepository.deleteById(userId);
    }
}
