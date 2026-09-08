package dev.zen.inventory.service;

import dev.zen.inventory.service.dto.requests.CreateProductRequest;
import dev.zen.inventory.service.dto.requests.CreateVariantRequest;
import dev.zen.inventory.service.dto.responses.ProductResponse;
import dev.zen.inventory.service.dto.responses.ProductVariantResponse;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);

    ProductVariantResponse addVariantToProduct(Long productId, CreateVariantRequest request);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getProductsByCategory(Long categoryId);
}