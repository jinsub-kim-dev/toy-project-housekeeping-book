package com.jinsub.housekeeping.api.category.model.dto;

import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 4577093109737437128L;

    @Getter
    @Builder
    public static class CreateRequest {
        private String categoryName;
        private TransactionType transactionType;
        private CategoryType categoryType;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResponse {
        private long categoryId;
        private String categoryName;
        private TransactionType transactionType;
        private CategoryType categoryType;

        public static CreateResponse of(Category category) {
            return CreateResponse.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .transactionType(category.getTransactionType())
                    .categoryType(category.getCategoryType())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadResponse {
        private long categoryId;
        private String categoryName;
        private TransactionType transactionType;
        private CategoryType categoryType;

        public static ReadResponse of(Category category) {
            return ReadResponse.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .transactionType(category.getTransactionType())
                    .categoryType(category.getCategoryType())
                    .build();
        }
    }
}
