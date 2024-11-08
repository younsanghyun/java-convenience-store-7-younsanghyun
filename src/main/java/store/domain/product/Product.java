package store.domain.product;

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
                quantity > 0 ? quantity + "개" : "재고 없음",
                promotionName != null && !promotionName.equals("null") ? " " + promotionName : ""
        );
    }
}
