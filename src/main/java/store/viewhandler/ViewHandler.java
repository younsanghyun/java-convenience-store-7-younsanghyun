package store.viewhandler;

import java.util.List;
import store.domain.product.Product;
import store.domain.receipt.Receipt;
import store.dto.OrderRequest;
import store.view.InputView;
import store.view.OutputView;

public class ViewHandler {
    private final InputView inputView;
    private final OutputView outputView;

    public ViewHandler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public OrderRequest readOrder() {
        return inputView.readOrder();
    }

    public boolean readUseMembership() {
        return inputView.readUseMembership();
    }

    public boolean readContinueShopping() {
        return inputView.readContinueShopping();
    }

    public void printWelcome() {
        outputView.printWelcome();
    }

    public void printProducts(List<Product> products) {
        outputView.printProducts(products);
    }

    public void printReceipt(Receipt receipt) {
        outputView.printReceipt(receipt);
    }

    public void printError(String message) {
        outputView.printError(message);
    }
}

