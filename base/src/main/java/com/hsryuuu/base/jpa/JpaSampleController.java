package com.hsryuuu.base.jpa;

import com.hsryuuu.base.jpa.model.UserDto;
import com.hsryuuu.base.jpa.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JpaSampleController {

    private final UserService userService;
    @PostMapping("/test")
    public UserDto test(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return userDto;
    }

    @GetMapping("/api/users")
    public List<UserDto> findUsers(){
        return userService.findUsers();
    }
}
