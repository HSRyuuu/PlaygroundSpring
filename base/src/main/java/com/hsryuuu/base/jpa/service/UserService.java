package com.hsryuuu.base.jpa.service;

import com.hsryuuu.base.jpa.model.UserDto;
import com.hsryuuu.base.jpa.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<UserDto> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::from)
                .toList();
    }
}
