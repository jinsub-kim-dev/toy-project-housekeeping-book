package com.jinsub.housekeeping.api.category.repository;

import com.jinsub.housekeeping.api.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);
}
