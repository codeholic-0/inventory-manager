package dev.zen.inventory.service.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record CreateWarehouseRequest(@NotBlank String name, @NotBlank String location) {

}
