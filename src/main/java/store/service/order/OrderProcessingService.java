package store.service.order;

import store.domain.order.OrderLine;
import store.domain.product.Product;
import store.domain.promotion.PromotionStock;
import store.dto.OrderItemRequest;
import store.service.promotion.PromotionStockService;
import store.viewhandler.ViewHandler;

public class OrderProcessingService{
    private final PromotionStockService promotionStockService;

    public OrderProcessingService(PromotionStockService promotionStockService) {
        this.promotionStockService = promotionStockService;
    }

    public OrderLine processOrder(OrderItemRequest item, ViewHandler viewHandler) {
        PromotionStock stock = promotionStockService.findPromotionStock(item.getProductName());
        return processPromotionPurchase(stock, item, viewHandler);
    }

    private OrderLine processPromotionPurchase(
            PromotionStock stock,
            OrderItemRequest item,
            ViewHandler viewHandler) {
        int requestQuantity = item.getQuantity();

        if (!stock.hasEnoughPromotionStock(requestQuantity)) {
            int regularPriceQuantity = stock.calculateRegularPriceQuantity(requestQuantity);
            requestQuantity = handleInsufficientStock(
                    stock, regularPriceQuantity, requestQuantity, viewHandler);
        }

        return createOrderLine(stock, requestQuantity);
    }

    private int handleInsufficientStock(
            PromotionStock stock,
            int regularPriceQuantity,
            int requestQuantity,
            ViewHandler viewHandler) {
        boolean acceptRegularPrice = viewHandler.readAcceptRegularPrice(
                stock.getProductName(),
                regularPriceQuantity
        );

        return acceptRegularPrice ? requestQuantity : requestQuantity - regularPriceQuantity;
    }

    private OrderLine createOrderLine(PromotionStock stock, int quantity) {
        Product product = stock.hasEnoughPromotionStock(quantity) ?
                stock.decreasePromotionStock(quantity) :
                stock.decreaseRegularStock(quantity);

        return new OrderLine(product, quantity);
    }
}

