package store.dto;

import store.constant.ErrorMesage;
import store.exception.InvalidInputException;

public class OrderItemRequest {
    private final String productName;
    private final int quantity;

    public OrderItemRequest(String productName, int quantity) {
        validateProductName(productName);
        validateQuantity(quantity);
        this.productName = productName;
        this.quantity = quantity;

    }

    private void validateProductName(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new InvalidInputException(ErrorMesage.INVALID_PRODUCT_NAME);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new InvalidInputException(ErrorMesage.INVALID_QUANTITY);
        }
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
