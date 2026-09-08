package dev.zen.inventory.persistence.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.zen.inventory.domain.models.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Optional<Warehouse> findById(Long id);

    List<Warehouse> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

}
