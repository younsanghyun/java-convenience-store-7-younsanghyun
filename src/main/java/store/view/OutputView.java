package store.view;

import java.util.List;
import store.domain.product.Product;
import store.domain.receipt.Receipt;

public class OutputView {
    public void printWelcome() {
        println("안녕하세요. W편의점입니다.");
        println("현재 보유하고 있는 상품입니다.\n");
    }

    public void printProducts(List<Product> products) {
        products.forEach(product -> println(product.getDisplayText()));
        println("");
    }

    public void printReceipt(Receipt receipt) {
        println("==============W 편의점================");
        printOrderLines(receipt);
        printFreeProducts(receipt);
        printSummary(receipt);
    }

    private void printOrderLines(Receipt receipt) {
        println("상품명\t\t수량\t금액");
        receipt.getOrderLines().forEach(
                line -> printf("%s\t\t%d\t%,d\n", line.getProductName(), line.getQuantity(), line.getAmount()));
    }

    private void printFreeProducts(Receipt receipt) {
        if (!receipt.getFreeProducts().isEmpty()) {
            println("=============증\t정===============");
            receipt.getFreeProducts()
                    .forEach(product -> printf("%s\t\t%d\n", product.getName(), product.getQuantity()));
        }
    }

    private void printSummary(Receipt receipt) {
        println("====================================");
        printf("총구매액\t\t%d\t%,d\n", receipt.getTotalQuantity(), receipt.getTotalAmount());
        printf("행사할인\t\t\t-%,d\n", receipt.getPromotionDiscountAmount());
        printf("멤버십할인\t\t\t-%,d\n", receipt.getMembershipDiscountAmount());
        printf("내실돈\t\t\t%,d\n", receipt.getFinalAmount());
    }

    public void printError(String message) {
        println(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}