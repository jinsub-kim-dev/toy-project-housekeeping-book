package com.jinsub.housekeeping.api.controller;

import com.jinsub.housekeeping.api.category.model.dto.CategoryDto;
import com.jinsub.housekeeping.api.category.model.dto.CreateCategoryRequestDto;
import com.jinsub.housekeeping.api.category.model.dto.CreateCategoryResponseDto;
import com.jinsub.housekeeping.api.category.model.dto.ReadCategoryResponseDto;
import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.category.service.CategoryReadService;
import com.jinsub.housekeeping.api.category.service.CategoryService;
import com.jinsub.housekeeping.base.model.CodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryReadService categoryReadService;

    @PostMapping({"", "/"})
    @ResponseBody
    public CodeResponse createCategory(@RequestBody CreateCategoryRequestDto request) {
        Category createdCategory = categoryService.createCategory(request.getCategoryName(), request.getTransactionType(), request.getCategoryType());
        CategoryDto categoryDto = CategoryDto.of(createdCategory);
        CreateCategoryResponseDto response = CreateCategoryResponseDto.builder().categoryDto(categoryDto).build();
        return CodeResponse.successResult(response);
    }

    @GetMapping("/id")
    @ResponseBody
    public CodeResponse readCategoryById(@RequestParam long categoryId) {
        Category readCategory = categoryReadService.getCategoryById(categoryId);
        CategoryDto categoryDto = CategoryDto.of(readCategory);
        ReadCategoryResponseDto response = ReadCategoryResponseDto.builder().categoryDto(categoryDto).build();
        return CodeResponse.successResult(response);
    }
}
