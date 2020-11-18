package com.jinsub.housekeeping.api.category.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryResponseDto implements Serializable {
    private static final long serialVersionUID = -4843348293282298535L;

    private CategoryDto categoryDto;
}
