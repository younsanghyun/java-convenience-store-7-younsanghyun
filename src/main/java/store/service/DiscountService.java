package store.service;

public class DiscountService {
    private static final int MEMBERSHIP_DISCOUNT_RATE = 30;
    private static final int MAX_MEMBERSHIP_DISCOUNT = 8000;

    public int calculateMembershipDiscount(int amount) {
        int discountAmount = (amount * MEMBERSHIP_DISCOUNT_RATE) / 100;
        return Math.min(discountAmount, MAX_MEMBERSHIP_DISCOUNT);
    }
}
