package com.jinsub.housekeeping.api.user.model.dto;

import com.jinsub.housekeeping.api.user.model.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserResponseDto implements Serializable {
    private static final long serialVersionUID = -3650800857276141884L;

    private long userId;
    private String userName;
    private String hashedEmail;
    private String hashedPassword;

    public static ReadUserResponseDto of(User user) {
        return ReadUserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .hashedEmail(user.getHashedEmail())
                .hashedPassword(user.getHashedPassword())
                .build();
    }
}
