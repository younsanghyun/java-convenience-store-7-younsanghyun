package store.controller;

import store.service.ProductService;
import store.service.PromotionService;

public class OrderController {
    private final ProductService productService;
    private final PromotionService promotionService;

    public OrderController(ProductService productService, PromotionService promotionService) {
        this.productService = productService;
        this.promotionService = promotionService;
    }
}
