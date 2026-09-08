package dev.zen.inventory.service.dto.responses;

import dev.zen.inventory.enums.StockStatus;

public record StockResponse(
        Long id,
        Long warehouseId,
        String warehouseName,
        Long variantId,
        String variantSku,
        Integer quantity,
        StockStatus status) {
}