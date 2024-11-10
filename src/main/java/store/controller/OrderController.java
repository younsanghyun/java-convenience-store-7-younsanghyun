package store.controller;

import store.domain.receipt.Receipt;
import store.dto.OrderRequest;
import store.service.OrderService;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

public class OrderController {
    private final OrderService orderService;
    private final InputView inputView;
    private final OutputView outputView;
    private final ProductService productService;

    public OrderController(
            OrderService orderService,
            InputView inputView,
            OutputView outputView,
            ProductService productService
    ) {
        this.orderService = orderService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.productService = productService;
    }

    public void start() {
        outputView.printWelcome();
        boolean continueShopping = true;

        while (continueShopping) {
            processOrder();
            continueShopping = inputView.readContinueShopping();
        }
    }

    private void processOrder() {
        try {
            outputView.printProducts(productService.getProduct());
            OrderRequest orderRequest = inputView.readOrder();
            boolean useMembership = inputView.readUseMembership();

            Receipt receipt = orderService.createOrder(orderRequest, useMembership);
            outputView.printReceipt(receipt);
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printError(e.getMessage());
            processOrder();
        }
    }
}