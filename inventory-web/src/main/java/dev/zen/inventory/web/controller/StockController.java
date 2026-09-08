package dev.zen.inventory.web.controller;

import dev.zen.inventory.service.StockService;
import dev.zen.inventory.service.dto.requests.UpdateStockRequest;
import dev.zen.inventory.service.dto.responses.StockResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PutMapping
    public ResponseEntity<StockResponse> updateStock(@Valid @RequestBody UpdateStockRequest request) {
        return ResponseEntity.ok(stockService.updateStock(request));
    }

    @GetMapping
    public ResponseEntity<StockResponse> getStock(
            @RequestParam Long warehouseId,
            @RequestParam Long variantId) {
        return ResponseEntity.ok(stockService.getStock(warehouseId, variantId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<StockResponse>> getStockByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(stockService.getStockByWarehouse(warehouseId));
    }
}