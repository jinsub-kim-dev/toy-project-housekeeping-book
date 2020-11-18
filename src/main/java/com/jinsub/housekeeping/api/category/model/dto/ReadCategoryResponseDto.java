package com.jinsub.housekeeping.api.category.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadCategoryResponseDto implements Serializable {
    private static final long serialVersionUID = 5875322821434707327L;

    private CategoryDto categoryDto;
}
