package dev.zen.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.zen.inventory.domain.exceptions.DomainException;
import dev.zen.inventory.domain.exceptions.EntityNotFoundException;
import dev.zen.inventory.domain.models.Category;
import dev.zen.inventory.service.CategoryService;
import dev.zen.inventory.service.dto.requests.CreateCategoryRequest;
import dev.zen.inventory.service.dto.responses.CategoryResponse;
import dev.zen.inventory.service.mapper.CategoryMapper;
import dev.zen.persistence.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest request) {
        if (categoryRepository.existsByName(request.name())) {
            throw new DomainException("Category name already exists: " + request.name());
        }

        Category parentCategory = null;
        if (request.parentCategoryId() != null) {
            parentCategory = categoryRepository.findById(request.parentCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category", request.parentCategoryId()));
        }

        Category category = categoryMapper.toEntity(request, parentCategory);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category", id));
        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllRootCategories() {
        return categoryRepository.findByParentCategoryIsNull().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> getSubCategories(Long parentId) {
        if (!categoryRepository.existsById(parentId)) {
            throw new EntityNotFoundException("Category", parentId);
        }
        return categoryRepository.findByParentCategoryId(parentId).stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
