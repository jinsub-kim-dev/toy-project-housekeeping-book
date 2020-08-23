package com.jinsub.housekeeping.api.user.model.dto;

import com.jinsub.housekeeping.api.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = -6667329984959810070L;

    @Getter
    @Builder
    public static class CreateRequest {
        private String userName;
        private String email;
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResponse {
        private long userId;
        private String userName;
        private String hashedEmail;
        private String hashedPassword;

        public static CreateResponse of(User user) {
            return CreateResponse.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .hashedEmail(user.getHashedEmail())
                    .hashedPassword(user.getHashedPassword())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadResponse {
        private long userId;
        private String userName;
        private String hashedEmail;
        private String hashedPassword;

        public static ReadResponse of(User user) {
            return ReadResponse.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .hashedEmail(user.getHashedEmail())
                    .hashedPassword(user.getHashedPassword())
                    .build();
        }
    }
}
