package dev.zen.inventory.service;

import dev.zen.inventory.service.dto.requests.CreateCategoryRequest;
import dev.zen.inventory.service.dto.responses.CategoryResponse;
import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest request);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllRootCategories();

    List<CategoryResponse> getSubCategories(Long parentId);
}