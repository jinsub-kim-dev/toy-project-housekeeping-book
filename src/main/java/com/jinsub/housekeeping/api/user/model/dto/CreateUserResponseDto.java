package com.jinsub.housekeeping.api.user.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseDto implements Serializable {
    private static final long serialVersionUID = -1554965449027306633L;

    private UserDto userDto;
}
