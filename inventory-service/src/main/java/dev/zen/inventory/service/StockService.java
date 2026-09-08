package dev.zen.inventory.service;

import dev.zen.inventory.service.dto.requests.UpdateStockRequest;
import dev.zen.inventory.service.dto.responses.StockResponse;
import java.util.List;

public interface StockService {
    StockResponse updateStock(UpdateStockRequest request);

    StockResponse getStock(Long warehouseId, Long variantId);

    List<StockResponse> getStockByWarehouse(Long warehouseId);
}