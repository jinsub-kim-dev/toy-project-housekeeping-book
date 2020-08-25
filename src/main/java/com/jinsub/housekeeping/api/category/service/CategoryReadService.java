package com.jinsub.housekeeping.api.category.service;

import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.repository.CategoryRepository;
import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;
import com.jinsub.housekeeping.base.exception.model.HouseKeepingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryReadService {

    // Repositories
    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new HouseKeepingException(HouseKeepingErrorType.POLICY_VIOLATION_NOT_EXIST_CATEGORY, "category is not exist"));
    }
}
