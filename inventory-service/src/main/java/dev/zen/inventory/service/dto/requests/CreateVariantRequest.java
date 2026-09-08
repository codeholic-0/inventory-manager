package dev.zen.inventory.service.dto.requests;

import java.math.BigDecimal;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateVariantRequest(
        @NotBlank String sku,
        @NotNull @Positive BigDecimal price,
        Map<String, String> attributes) {

}
