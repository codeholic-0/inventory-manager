package dev.zen.inventory.domain.exceptions;

import lombok.Getter;

@Getter
public class InsufficientStockException extends DomainException {

    private final Integer requestedQuantity;
    private final Integer availableQuantity;

    public InsufficientStockException(Integer requested, Integer available) {
        this.requestedQuantity = requested;
        this.availableQuantity = available;

        super(String.format("Insufficient stock: requested %d units, but only %d available.",
                requested, available));
    }
}
