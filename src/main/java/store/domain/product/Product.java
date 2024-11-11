package store.domain.product;

import store.constant.ErrorMesage;
import store.exception.OutOfStockException;

public class Product {
    private final String name;
    private final int price;
    private final int quantity;
    private final String promotionName;

    public Product(String name, int price, int quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public String getDisplayText() {
        StringBuilder text = new StringBuilder("- ").append(name).append(" ").append(String.format("%,d", price))
                .append("원 ").append(formatQuantityText());

        if (hasPromotion()) {
            text.append(formatPromotionText());
        }
        return text.toString();
    }

    private String formatQuantityText() {
        if (quantity <= 0) {
            return "재고 없음";
        }
        return quantity + "개";
    }

    private String formatPromotionText() {
        return " " + promotionName;
    }

    public boolean hasPromotion() {
        return promotionName != null && !promotionName.equals("null");
    }

    public boolean canPurchase(int requestQuantity) {
        return quantity >= requestQuantity;
    }

    public Product decreaseQuantity(int purchaseQuantity) {
        validatePurchaseQuantity(purchaseQuantity);
        return new Product(name, price, quantity - purchaseQuantity, promotionName);
    }

    private void validatePurchaseQuantity(int purchaseQuantity) {
        if (!canPurchase(purchaseQuantity)) {
            throw new OutOfStockException(ErrorMesage.OUT_OF_STOCK);
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
