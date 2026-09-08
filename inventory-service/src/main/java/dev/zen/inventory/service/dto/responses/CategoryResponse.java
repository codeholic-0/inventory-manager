package dev.zen.inventory.service.dto.responses;

public record CategoryResponse(
        Long id,
        String name,
        Long parentCategoryId) {
}
