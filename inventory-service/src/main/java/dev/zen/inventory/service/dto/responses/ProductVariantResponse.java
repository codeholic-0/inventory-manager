package dev.zen.inventory.service.dto.responses;

import java.math.BigDecimal;
import java.util.Map;

public record ProductVariantResponse(
        Long id,
        String sku,
        BigDecimal price,
        Map<String, String> attributes) {
}
