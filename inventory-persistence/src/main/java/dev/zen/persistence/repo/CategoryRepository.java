package dev.zen.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zen.inventory.domain.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    List<Category> findByParentCategoryIsNull();

    List<Category> findByParentCategory(Category parentCategory);

    List<Category> findByParentCategoryId(Long parentCategoryId);

    boolean existsByName(String name);
}
