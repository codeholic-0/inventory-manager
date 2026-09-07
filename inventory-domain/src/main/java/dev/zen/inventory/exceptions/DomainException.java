package dev.zen.inventory.exceptions;

public class DomainException extends RuntimeException {
    public DomainException(String messege) {
        super(messege);
    }

    public DomainException(String messege, Throwable cause) {
        super(messege, cause);
    }
}
