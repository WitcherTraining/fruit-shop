package com.epam.springboot.fruitshop.api.v1.mapper;

import com.epam.springboot.fruitshop.api.v1.model.CategoryDTO;
import com.epam.springboot.fruitshop.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String FRUITS = "fruits";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(ID);
        category.setName(FRUITS);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(FRUITS, categoryDTO.getName());
    }
}