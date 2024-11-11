package store.domain.promotion;

import store.domain.product.Product;

public class PromotionStock {
    private final Product promotionProduct;
    private final Product regularProduct;

    public PromotionStock(Product promotionProduct, Product regularProduct) {
        this.promotionProduct = promotionProduct;
        this.regularProduct = regularProduct;
    }

    public boolean hasEnoughPromotionStock(int requestQuantity) {
        return promotionProduct.hasPromotion() &&
                promotionProduct.getQuantity() >= requestQuantity;
    }

    public int calculateRegularPriceQuantity(int requestQuantity) {
        return requestQuantity - promotionProduct.getQuantity();
    }

    public Product decreasePromotionStock(int quantity) {
        return promotionProduct.decreaseQuantity(quantity);
    }

    public Product decreaseRegularStock(int quantity) {
        return regularProduct.decreaseQuantity(quantity);
    }

    public String getProductName() {
        return promotionProduct.getName();
    }
}

