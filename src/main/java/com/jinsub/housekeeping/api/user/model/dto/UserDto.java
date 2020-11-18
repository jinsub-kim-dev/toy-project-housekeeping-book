package com.jinsub.housekeeping.api.user.model.dto;

import com.jinsub.housekeeping.api.user.model.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = -6667329984959810070L;

    private long userId;
    private String userName;
    private String hashedEmail;
    private String hashedPassword;

    public static UserDto of(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .hashedEmail(user.getHashedEmail())
                .hashedPassword(user.getHashedPassword())
                .build();
    }
}
