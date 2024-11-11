package store.service.discount;

import store.constant.MembershipConstants;

public class DiscountService {
    public int calculateMembershipDiscount(int amount) {
        int discountAmount = (amount * MembershipConstants.DISCOUNT_RATE) / 100;
        return Math.min(discountAmount, MembershipConstants.MAX_DISCOUNT_AMOUNT);
    }
}
