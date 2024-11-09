package store.service;

import java.time.Clock;
import java.util.List;
import store.domain.promotion.FreeProduct;
import store.repository.PromotionRepository;

public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final Clock clock;

    public PromotionService(PromotionRepository promotionRepository, Clock clock) {
        this.promotionRepository = promotionRepository;
        this.clock = clock;
    }

    public List<FreeProduct>
}
