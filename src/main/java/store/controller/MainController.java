package store.controller;

import store.viewhandler.ViewHandler;

public class MainController {
    private final OrderController orderController;
    private final ReceiptController receiptController;
    private final ViewHandler viewHandler;

    public MainController(
            OrderController orderController,
            ReceiptController receiptController,
            ViewHandler viewHandler
    ) {
        this.orderController = orderController;
        this.receiptController = receiptController;
        this.viewHandler = viewHandler;
    }

    public void start() {
        viewHandler.printWelcome();
        boolean continueShopping = true;

        while (continueShopping) {
            processOneOrder();
            continueShopping = viewHandler.readContinueShopping();
        }
    }

    private void processOneOrder() {
        try {
            var receipt = orderController.createOrder();
            receiptController.printReceipt(receipt);
        } catch (IllegalArgumentException | IllegalStateException e) {
            viewHandler.printError(e.getMessage());
            processOneOrder();
        }
    }
}
