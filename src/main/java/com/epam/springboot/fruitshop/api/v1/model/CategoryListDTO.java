package com.epam.springboot.fruitshop.api.v1.model;

import com.epam.springboot.fruitshop.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

    private List<CategoryDTO> categories;
}
