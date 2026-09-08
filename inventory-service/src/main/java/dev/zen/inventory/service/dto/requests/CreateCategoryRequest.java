package dev.zen.inventory.service.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank(message = "Name requied!") String name,
        Long parentCategoryId) {

}
