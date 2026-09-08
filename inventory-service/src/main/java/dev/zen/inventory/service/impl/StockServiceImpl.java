package dev.zen.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.zen.inventory.domain.models.ProductVariant;
import dev.zen.inventory.domain.models.Stock;
import dev.zen.inventory.domain.models.Warehouse;
import dev.zen.inventory.enums.StockStatus;
import dev.zen.inventory.exceptions.DomainException;
import dev.zen.inventory.exceptions.EntityNotFoundException;
import dev.zen.inventory.service.StockService;
import dev.zen.inventory.service.dto.requests.UpdateStockRequest;
import dev.zen.inventory.service.dto.responses.StockResponse;
import dev.zen.persistence.repo.ProductVariantRepository;
import dev.zen.persistence.repo.StockRepository;
import dev.zen.persistence.repo.WarehouseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductVariantRepository variantRepository;

    @Override
    @Transactional
    public StockResponse updateStock(UpdateStockRequest request) {
        Warehouse warehouse = warehouseRepository.findById(request.warehouseId())
                .orElseThrow(() -> new EntityNotFoundException("Warehouse", request.warehouseId()));

        ProductVariant variant = variantRepository.findById(request.variantId())
                .orElseThrow(() -> new EntityNotFoundException("ProductVariant", request.variantId()));

        Stock stock = stockRepository.findByWarehouseIdAndProductVariantId(request.warehouseId(), request.variantId())
                .orElseGet(() -> {
                    Stock newStock = new Stock();
                    newStock.setWarehouse(warehouse);
                    newStock.setProductVariant(variant);
                    return newStock;
                });

        stock.setQuantity(request.quantity());
        Stock saved = stockRepository.save(stock);

        return mapToStockResponse(saved);
    }

    @Override
    public StockResponse getStock(Long warehouseId, Long variantId) {
        Stock stock = stockRepository.findByWarehouseIdAndProductVariantId(warehouseId, variantId)
                .orElseThrow(() -> new DomainException(
                        String.format("Stock record not found for warehouse %d and variant %d", warehouseId, variantId)));
        return mapToStockResponse(stock);
    }

    @Override
    public List<StockResponse> getStockByWarehouse(Long warehouseId) {
        if (!warehouseRepository.existsById(warehouseId)) {
            throw new EntityNotFoundException("Warehouse", warehouseId);
        }
        return stockRepository.findByWarehouseId(warehouseId).stream()
                .map(this::mapToStockResponse)
                .toList();
    }

    private StockResponse mapToStockResponse(Stock stock) {
        StockStatus status;
        if (stock.getQuantity() <= 0) {
            status = StockStatus.OUT_OF_STOCK;
        } else if (stock.getQuantity() < 10) {
            status = StockStatus.LOW_STOCK;
        } else {
            status = StockStatus.IN_STOCK;
        }

        return new StockResponse(
                stock.getId(),
                stock.getWarehouse().getId(),
                stock.getWarehouse().getName(),
                stock.getProductVariant().getId(),
                stock.getProductVariant().getSku(),
                stock.getQuantity(),
                status
        );
    }
}
