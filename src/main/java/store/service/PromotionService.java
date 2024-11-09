package store.service;

import java.time.Clock;
import store.domain.order.OrderLine;
import store.repository.PromotionRepository;

public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final Clock clock;

    public PromotionService(PromotionRepository promotionRepository, Clock clock) {
        this.promotionRepository = promotionRepository;
        this.clock = clock;
    }

    private boolean isPromotionApplicable(OrderLine orderLine) {
        return orderLine.getProduct().hasPromotion() &&
                isPromotionValid(orderLine.getProduct().getPromotionName());
    }

    private boolean isPromotionValid(String promotionName) {
    }

}
