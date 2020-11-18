package com.jinsub.housekeeping.api.user.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserResponseDto implements Serializable {
    private static final long serialVersionUID = -3650800857276141884L;

    private UserDto userDto;
}
