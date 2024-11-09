package store.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import store.domain.order.OrderLine;
import store.domain.promotion.FreeProduct;
import store.domain.promotion.PromotionType;
import store.repository.PromotionRepository;

public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final Clock clock;

    public PromotionService(PromotionRepository promotionRepository, Clock clock) {
        this.promotionRepository = promotionRepository;
        this.clock = clock;
    }

    public List<FreeProduct> applyPromotions(List<OrderLine> orderLines) {
        return orderLines.stream()
                .filter(this::isPromotionApplicable)
                .map(this::createFreeProduct)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private boolean isPromotionApplicable(OrderLine orderLine) {
        return orderLine.getProduct().hasPromotion() &&
                isPromotionValid(orderLine.getProduct().getPromotionName());
    }

    private boolean isPromotionValid(String promotionName) {
        return promotionRepository.findByName(promotionName)
                .map(promotion -> promotion.isValid(LocalDateTime.now(clock)))
                .orElse(false);
    }

    private Optional<FreeProduct> createFreeProduct(OrderLine orderLine) {
        PromotionType promotionType = PromotionType.fromName(orderLine.getProduct().getPromotionName());
        int freeQuantity = promotionType.calculateFreeQuantity(orderLine.getQuantity());

        return freeQuantity > 0 ?
                Optional.of(new FreeProduct(orderLine.getProduct(), freeQuantity)) :
                Optional.empty();
    }
}
