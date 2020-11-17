package com.jinsub.housekeeping.api.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto implements Serializable {
    private static final long serialVersionUID = 5586229564666093482L;

    private String userName;
    private String email;
    private String password;
}
