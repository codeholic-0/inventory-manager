package dev.zen.inventory.service.mapper;

import dev.zen.inventory.domain.models.Category;
import dev.zen.inventory.domain.models.Product;
import dev.zen.inventory.domain.models.ProductVariant;
import dev.zen.inventory.service.dto.requests.CreateProductRequest;
import dev.zen.inventory.service.dto.requests.CreateVariantRequest;
import dev.zen.inventory.service.dto.responses.ProductResponse;
import dev.zen.inventory.service.dto.responses.ProductVariantResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request, Category category) {
        Product product = new Product();
        product.setName(request.name());
        product.setDesc(request.desc());
        product.setCategory(category);
        return product;
    }

    public ProductVariant toVariantEntity(CreateVariantRequest request, Product product) {
        ProductVariant variant = new ProductVariant();
        variant.setSku(request.sku());
        variant.setPrice(request.price());
        variant.setAttributes(request.attributes());
        variant.setProduct(product);
        return variant;
    }

    public ProductVariantResponse toVariantResponse(ProductVariant variant) {
        return new ProductVariantResponse(
                variant.getId(),
                variant.getSku(),
                variant.getPrice(),
                variant.getAttributes());
    }

    public ProductResponse toResponse(Product product) {
        List<ProductVariantResponse> variantResponses = product.getVariants() != null
                ? product.getVariants().stream().map(this::toVariantResponse).toList()
                : Collections.emptyList();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDesc(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null,
                variantResponses);
    }
}