package com.epam.springboot.fruitshop.services;

import com.epam.springboot.fruitshop.api.v1.mapper.CategoryMapper;
import com.epam.springboot.fruitshop.api.v1.model.CategoryDTO;
import com.epam.springboot.fruitshop.domain.Category;
import com.epam.springboot.fruitshop.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final String NAME = "fruits";
    public static final long ID = 1L;
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        assertEquals(3, categoryDTOList.size());
    }

    @Test
    void getCategoryByName() {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(ID, categoryDTO.getId());
    }
}