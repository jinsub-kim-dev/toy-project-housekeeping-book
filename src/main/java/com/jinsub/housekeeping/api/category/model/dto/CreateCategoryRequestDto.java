package com.jinsub.housekeeping.api.category.model.dto;

import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequestDto implements Serializable {
    private static final long serialVersionUID = -6940699907043339425L;

    private String categoryName;
    private TransactionType transactionType;
    private CategoryType categoryType;
}
