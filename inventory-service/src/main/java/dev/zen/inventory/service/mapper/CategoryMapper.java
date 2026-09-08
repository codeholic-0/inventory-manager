package dev.zen.inventory.service.mapper;

import dev.zen.inventory.domain.models.Category;
import dev.zen.inventory.service.dto.requests.CreateCategoryRequest;
import dev.zen.inventory.service.dto.responses.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequest request, Category parentCategory) {
        Category category = new Category();
        category.setName(request.name());
        category.setParentCategory(parentCategory);
        return category;
    }

    public CategoryResponse toResponse(Category category) {
        Long parentId = category.getParentCategory() != null ? category.getParentCategory().getId() : null;
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                parentId);
    }
}