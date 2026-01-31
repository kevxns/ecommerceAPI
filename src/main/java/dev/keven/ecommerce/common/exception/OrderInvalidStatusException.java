package dev.keven.ecommerce.common.exception;

public class OrderInvalidStatusException extends RuntimeException {
    public OrderInvalidStatusException(String message) {
        super(message);
    }
}
