package com.hsryuuu.webflux.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private long id;
    private String name;
    private int age;
}
