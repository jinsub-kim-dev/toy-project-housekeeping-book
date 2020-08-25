package com.jinsub.housekeeping.api.category.service;

import com.jinsub.housekeeping.api.category.enums.CategoryType;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.repository.CategoryRepository;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    // Repositories
    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(String categoryName, TransactionType transactionType, CategoryType categoryType) {
        if (categoryRepository.existsByCategoryName(categoryName)) {
            throw new HouseKeepingException(HouseKeepingErrorType.POLICY_VIOLATION_ALREADY_EXIST_CATEGORY, "category is already exist");
        }

        return  categoryRepository.save(Category.builder()
                .categoryName(categoryName)
                .transactionType(transactionType)
                .categoryType(categoryType)
                .build());
    }
}
