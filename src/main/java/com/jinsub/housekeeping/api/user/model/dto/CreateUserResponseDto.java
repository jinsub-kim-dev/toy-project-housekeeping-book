package com.jinsub.housekeeping.api.user.model.dto;

import com.jinsub.housekeeping.api.user.model.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseDto implements Serializable {
    private static final long serialVersionUID = -1554965449027306633L;

    private long userId;
    private String userName;
    private String hashedEmail;
    private String hashedPassword;

    public static CreateUserResponseDto of(User user) {
        return CreateUserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .hashedEmail(user.getHashedEmail())
                .hashedPassword(user.getHashedPassword())
                .build();
    }
}
