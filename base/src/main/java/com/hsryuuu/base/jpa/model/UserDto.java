package com.hsryuuu.base.jpa.model;

import com.hsryuuu.base.jpa.entity.AppUser;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private UUID id;
    private String name;
    private UUID groupId;
    private String groupName;

    public static UserDto from(AppUser user) {
            return UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .groupId(user.getGroup().getId())
                    .groupName(user.getGroup().getName())
                    .build();
    }
}
