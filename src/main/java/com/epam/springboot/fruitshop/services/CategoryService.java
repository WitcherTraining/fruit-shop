package com.epam.springboot.fruitshop.services;

import com.epam.springboot.fruitshop.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
