package dev.zen.inventory.service.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateStockRequest(
        @NotNull Long warehouseId,
        @NotNull Long variantId,
        @NotNull @Min(value = 0) Integer quantity) {
}
