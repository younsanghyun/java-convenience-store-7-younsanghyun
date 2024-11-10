package store.validator;

import store.constant.ErrorMesage;
import store.domain.product.Product;
import store.exception.InvalidInputException;
import store.exception.OutOfStockException;

public class ProductValidator {
    public void validateProduct(Product product, String name) {
        validateExists(product, name);
    }

    private void validateExists(Product product, String name) {
        if (product == null) {
            throw new InvalidInputException(ErrorMesage.PRODUCT_NOT_FOUND);
        }
    }

    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new InvalidInputException(ErrorMesage.INVALID_QUANTITY);
        }
    }

    public void validateStock(Product product, int requestQuantity) {
        if (!product.canPurchase(requestQuantity)) {
            throw new OutOfStockException(ErrorMesage.OUT_OF_STOCK);
        }
    }
}