package store.controller;

import store.service.DiscountService;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;

public class OrderController {
    private final ProductService productService;
    private final PromotionService promotionService;
    private final OrderService orderService;
    private final DiscountService discountService;

    public OrderController(
            ProductService productService,
            PromotionService promotionService,
            OrderService orderService,
            DiscountService discountService) {
        this.productService = productService;
        this.promotionService = promotionService;
        this.orderService = orderService;
        this.discountService = discountService;

    }


}
