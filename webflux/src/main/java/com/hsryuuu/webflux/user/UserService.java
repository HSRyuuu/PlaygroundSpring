package com.hsryuuu.webflux.user;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class UserService {

    private final Map<Long, User> users =
            Map.of(1L, new User(1L, "Kim", 20),
                    2L, new User(2L, "Jack", 30),
                    3L, new User(3L, "Park", 40),
                    4L, new User(4L, "Lee", 50));

    public Flux<User> getAllUsers() {
        return Flux.fromStream(users.values().stream());
    }

    public Mono<User> getUserById(long id) {
        return Mono.just(users.get(id));
    }
}
