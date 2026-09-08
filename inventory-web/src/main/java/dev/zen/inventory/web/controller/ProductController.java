package dev.zen.inventory.web.controller;

import dev.zen.inventory.service.ProductService;
import dev.zen.inventory.service.dto.requests.CreateProductRequest;
import dev.zen.inventory.service.dto.requests.CreateVariantRequest;
import dev.zen.inventory.service.dto.responses.ProductResponse;
import dev.zen.inventory.service.dto.responses.ProductVariantResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/variants")
    public ResponseEntity<ProductVariantResponse> addVariantToProduct(
            @PathVariable Long id,
            @Valid @RequestBody CreateVariantRequest request) {
        ProductVariantResponse response = productService.addVariantToProduct(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }
}