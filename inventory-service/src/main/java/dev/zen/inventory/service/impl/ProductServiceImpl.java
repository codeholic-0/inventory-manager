package dev.zen.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.zen.inventory.domain.exceptions.DomainException;
import dev.zen.inventory.domain.exceptions.EntityNotFoundException;
import dev.zen.inventory.domain.models.Category;
import dev.zen.inventory.domain.models.Product;
import dev.zen.inventory.domain.models.ProductVariant;
import dev.zen.inventory.persistence.repo.CategoryRepository;
import dev.zen.inventory.persistence.repo.ProductRepository;
import dev.zen.inventory.persistence.repo.ProductVariantRepository;
import dev.zen.inventory.service.ProductService;
import dev.zen.inventory.service.dto.requests.CreateProductRequest;
import dev.zen.inventory.service.dto.requests.CreateVariantRequest;
import dev.zen.inventory.service.dto.responses.ProductResponse;
import dev.zen.inventory.service.dto.responses.ProductVariantResponse;
import dev.zen.inventory.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository variantRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        if (productRepository.existsByName(request.name())) {
            throw new DomainException("Product already exists with name: " + request.name());
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category", request.categoryId()));

        Product product = productMapper.toEntity(request, category);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ProductVariantResponse addVariantToProduct(Long productId, CreateVariantRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product", productId));

        if (variantRepository.existsBySku(request.sku())) {
            throw new DomainException("Variant SKU already exists: " + request.sku());
        }

        ProductVariant variant = productMapper.toVariantEntity(request, product);
        ProductVariant saved = variantRepository.save(variant);
        return productMapper.toVariantResponse(saved);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
