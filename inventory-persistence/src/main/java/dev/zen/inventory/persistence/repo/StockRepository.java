package dev.zen.inventory.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zen.inventory.domain.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock>	findById(Long id);

    Optional<Stock>	findByWarehouseIdAndProductVariantId(Long warehouseId, Long variantId);

    List<Stock>	findByWarehouseId(Long warehouseId);

    List<Stock>	findByProductVariantId(Long variantId);

    List<Stock>	findByQuantityLessThan(Integer threshold);
}
