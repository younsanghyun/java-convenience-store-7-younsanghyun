package store.domain.promotion;

import store.domain.product.Product;

public class FreeProduct {
    private final Product product;
    private final int quantity;

    public FreeProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getName() {
        return product.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDiscountAmount() {
        return product.getPrice() * quantity;
    }
}
