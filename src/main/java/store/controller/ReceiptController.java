package store.controller;

import store.domain.receipt.Receipt;
import store.viewhandler.ViewHandler;

public class ReceiptController {
    private final ViewHandler viewHandler;

    public ReceiptController(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public void printReceipt(Receipt receipt) {
        viewHandler.printReceipt(receipt);
    }
}

