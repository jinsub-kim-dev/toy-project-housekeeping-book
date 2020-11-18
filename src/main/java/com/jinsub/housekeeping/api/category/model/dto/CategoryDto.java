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
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 4577093109737437128L;

    private long categoryId;
    private String categoryName;
    private TransactionType transactionType;
    private CategoryType categoryType;

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .transactionType(category.getTransactionType())
                .categoryType(category.getCategoryType())
                .build();
    }
}
