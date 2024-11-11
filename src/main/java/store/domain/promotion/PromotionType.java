package store.domain.promotion;

import java.util.Arrays;
import store.constant.ErrorMesage;
import store.exception.InvalidPromotionException;

public enum PromotionType {
    TWO_PLUS_ONE("탄산2+1", 2, 1), ONE_PLUS_ONE("MD추천상품", 1, 1), FLASH_SALE("반짝할인", 1, 1);

    private final String name;
    private final int buyQuantity;
    private final int freeQuantity;

    PromotionType(String name, int buyQuantity, int freeQuantity) {
        this.name = name;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
    }

    public static PromotionType fromName(String name) {
        return Arrays.stream(values()).filter(type -> type.name.equals(name)).findFirst()
                .orElseThrow(() -> new InvalidPromotionException(ErrorMesage.INVALID_PRODUCT_NAME));
    }

    public int calculateFreeQuantity(int purchaseQuantity) {
        return (purchaseQuantity / buyQuantity) * freeQuantity;
    }

    public String getName() {
        return name;
    }

}
