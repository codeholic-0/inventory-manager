package dev.zen.persistence.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zen.inventory.domain.models.ProductVariant;
import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Optional<ProductVariant> findById(Long id);

    Optional<ProductVariant> findBySku(String sku);

    List<ProductVariant> findByProductId(Long productId);

    boolean existsBySku(String sku);
}
