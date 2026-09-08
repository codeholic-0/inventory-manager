package dev.zen.inventory.persistence.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zen.inventory.domain.models.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);
}
