package dev.zen.inventory.service.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
        @NotBlank String name,
        @NotBlank String desc,
        @NotNull Long categoryId) {
}
