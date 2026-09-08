package dev.zen.inventory.domain.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String messege) {
        super(messege);
    }

    public DomainException(String messege, Throwable cause) {
        super(messege, cause);
    }
}
