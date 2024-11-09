package store.service;

import store.validator.ProductValidator;

public class OrderService {
    private final ProductService productService;
    private final PromotionService promotionService;
    private final DiscountService discountService;
    private final ProductValidator productValidator;

    public OrderService(
            ProductService productService,
            PromotionService promotionService,
            DiscountService discountService,
            ProductValidator productValidator
    ) {
        this.productService = productService;
        this.promotionService = promotionService;
        this.discountService = discountService;
        this.productValidator = productValidator;
    }
}
