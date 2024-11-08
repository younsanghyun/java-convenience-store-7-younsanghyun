package store.validator;

import store.domain.product.Product;

public class ProductValidator {
    public void validateProduct(Product product, String name) {
        validateExists(product, name);
    }

    private void validateExists(Product product, String name) {
        if (product == null) {
            throw new InvalidInputException(ErrorMessage.PRODUCT_NOT_FOUND);
    }
}