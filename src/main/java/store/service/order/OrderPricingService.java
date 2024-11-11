package store.service.order;

import java.util.List;
import store.domain.order.OrderLine;
import store.domain.promotion.FreeProduct;
import store.domain.receipt.Receipt;
import store.service.discount.DiscountService;
import store.service.promotion.PromotionService;

public class OrderPricingService {
    private final PromotionService promotionService;
    private final DiscountService discountService;

    public OrderPricingService(PromotionService promotionService, DiscountService discountService) {
        this.promotionService = promotionService;
        this.discountService = discountService;
    }

    public Receipt calculateFinalPrice(List<OrderLine> orderLines, boolean useMembership) {
        List<FreeProduct> freeProducts = promotionService.applyPromotions(orderLines);
        int totalAmount = calculateTotalAmount(orderLines);
        int promotionDiscount = calculatePromotionDiscount(freeProducts);
        int membershipDiscount = calculateMembershipDiscount(totalAmount - promotionDiscount, useMembership);

        return new Receipt(orderLines, freeProducts, totalAmount, promotionDiscount, membershipDiscount);
    }

    private int calculateTotalAmount(List<OrderLine> orderLines) {
        return orderLines.stream().mapToInt(OrderLine::getAmount).sum();
    }

    private int calculatePromotionDiscount(List<FreeProduct> freeProducts) {
        return freeProducts.stream().mapToInt(FreeProduct::getDiscountAmount).sum();
    }

    private int calculateMembershipDiscount(int amountAfterPromotion, boolean useMembership) {
        return useMembership ? discountService.calculateMembershipDiscount(amountAfterPromotion) : 0;
    }
}
