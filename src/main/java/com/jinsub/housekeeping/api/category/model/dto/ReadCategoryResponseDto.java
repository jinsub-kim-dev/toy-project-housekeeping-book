package com.jinsub.housekeeping.api.category.model.dto;

import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadCategoryResponseDto implements Serializable {
    private static final long serialVersionUID = 5875322821434707327L;

    private long categoryId;
    private String categoryName;
    private TransactionType transactionType;
    private CategoryType categoryType;

    public static ReadCategoryResponseDto of(Category category) {
        return ReadCategoryResponseDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .transactionType(category.getTransactionType())
                .categoryType(category.getCategoryType())
                .build();
    }
}
