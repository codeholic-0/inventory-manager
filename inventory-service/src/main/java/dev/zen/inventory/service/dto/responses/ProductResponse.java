package dev.zen.inventory.service.dto.responses;

import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String desc,
        Long categoryId,
        String categoryName,
        List<ProductVariantResponse> variants) {
}
