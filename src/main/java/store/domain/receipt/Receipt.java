package store.domain.receipt;

import java.util.ArrayList;
import java.util.List;
import store.domain.order.OrderLine;
import store.domain.promotion.FreeProduct;

public class Receipt {
    private final List<OrderLine> orderLines;
    private final List<FreeProduct> freeProducts;
    private final int totalAmount;
    private final int promotionDiscountAmount;
    private final int membershipDiscountAmount;

    public Receipt(
            List<OrderLine> orderLines,
            List<FreeProduct> freeProducts,
            int totalAmount,
            int promotionDiscountAmount,
            int membershipDiscountAmount
    ) {
        this.orderLines = new ArrayList<>(orderLines);
        this.freeProducts = new ArrayList<>(freeProducts);
        this.totalAmount = totalAmount;
        this.promotionDiscountAmount = promotionDiscountAmount;
        this.membershipDiscountAmount = membershipDiscountAmount;
    }

    public List<OrderLine> getOrderLines() {
        return new ArrayList<>(orderLines);
    }

    public List<FreeProduct> getFreeProducts() {
        return new ArrayList<>(freeProducts);
    }

    public int getTotalQuantity() {
        return orderLines.stream()
                .mapToInt(OrderLine::getQuantity)
                .sum();
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getPromotionDiscountAmount() {
        return promotionDiscountAmount;
    }

    public int getMembershipDiscountAmount() {
        return membershipDiscountAmount;
    }

    public int getFinalAmount() {
        return totalAmount - promotionDiscountAmount - membershipDiscountAmount;
    }
}

