package com.epam.springboot.fruitshop.api.v1.mapper;

import com.epam.springboot.fruitshop.api.v1.model.CategoryDTO;
import com.epam.springboot.fruitshop.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
