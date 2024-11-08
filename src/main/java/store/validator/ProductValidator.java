package store.validator;

import store.constant.ErrorMesage;
import store.domain.product.Product;
import store.exception.InvalidInputException;

public class ProductValidator {
    public void validateProduct(Product product, String name) {
        validateExists(product, name);
    }

    private void validateExists(Product product, String name) {
        if (product == null) {
            throw new InvalidInputException(ErrorMesage.PRODUCT_NOT_FOUND);
        }
    }
}