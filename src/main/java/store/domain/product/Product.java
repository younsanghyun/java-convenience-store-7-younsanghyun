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
        return String.format("- %s %,d원 %s%s",
                name,
                price,
                formatStockText(),
                formatPromotionText()
        );
    }
    private String formatStockText() {
        return hasStock() ? quantity + "개" : "재고 없음";
    }

    private String formatPromotionText() {
        return hasPromotion() ? " " + promotionName : "";
    }

    public boolean hasStock() {
        return quantity > 0;
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
