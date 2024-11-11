package store.controller;

import store.domain.receipt.Receipt;
import store.dto.OrderRequest;
import store.service.OrderService;
import store.service.ProductService;
import store.viewhandler.ViewHandler;

public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final ViewHandler viewHandler;

    public OrderController(
            OrderService orderService,
            ProductService productService,
            ViewHandler viewHandler
    ) {
        this.orderService = orderService;
        this.productService = productService;
        this.viewHandler = viewHandler;
    }

    public Receipt createOrder() {
        viewHandler.printProducts(productService.getProduct());
        OrderRequest orderRequest = viewHandler.readOrder();
        boolean useMembership = viewHandler.readUseMembership();

        return orderService.createOrder(orderRequest, useMembership);
    }
}


